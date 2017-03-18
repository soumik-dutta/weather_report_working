package com.omoto.configurator.azure;

import com.microsoft.azure.management.Azure;
import com.microsoft.azure.management.compute.Disk;
import com.microsoft.azure.management.compute.KnownLinuxVirtualMachineImage;
import com.microsoft.azure.management.compute.VirtualMachine;
import com.microsoft.azure.management.compute.VirtualMachineSizeTypes;
import com.microsoft.azure.management.resources.fluentcore.arm.Region;
import com.microsoft.azure.management.resources.fluentcore.model.Creatable;
import com.microsoft.rest.LogLevel;
import com.omoto.configurator.util.AzureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Creating a Linux VM in Azure server
 * Created by omoto on 17/2/17.
 */
@Slf4j
public class CreateMachine {

    private Azure azure;

    /**
     * Initailizing Azure object
     */
    public CreateMachine() {
        try {
            this.azure = Azure.configure()
                    .withLogLevel(LogLevel.BASIC)
                    .authenticate(credFile)
                    .withDefaultSubscription();


        } catch (IOException e) {
            log.info(e.toString());
            e.printStackTrace();

        }
    }


    Region region = Region.ASIA_SOUTHEAST;
    final File credFile = new File(System.getenv("AZURE_AUTH_LOCATION"));


    //variables from properties
    @Value("${azure.vmName}")
    String linuxVmName;
    @Value("${azure.rgName}")
    String rgName;
    @Value("${azure.network}")
    String network;
    @Value("${azure.userName}")
    String userName;
    @Value("${azure.password}")
    String password;





    /**
     * Create new Linux Machine
     * 2017-03-18T11:59:52.188Z
     */
    public VirtualMachine createLinuxMachine() {
        System.out.println("Creating a Linux VM in the network");

        Date t1 = new Date();

        //createing azure disk and later attach this disk to the VM
        // Prepare a creatable data disk for VM
        Creatable<Disk> dataDiskCreatable = azure.disks().define(AzureUtil.createRandomName("dsk-"))
                .withRegion(region)
                .withExistingResourceGroup(rgName)
                .withData()
                .withSizeInGB(100);

        // Create a data disk to attach to VM
        Disk dataDisk = azure.disks().define(AzureUtil.createRandomName("dsk-"))
                .withRegion(region)
                .withNewResourceGroup(rgName)
                .withData()
                .withSizeInGB(50)
                .create();

        //Creating Linux VM with BASIC_AO size
        VirtualMachine linuxVM = azure.virtualMachines().define(linuxVmName)
                .withRegion(region)
                .withExistingResourceGroup(rgName)
                .withNewPrimaryNetwork("10.0.0.0/28")
                .withPrimaryPrivateIpAddressDynamic()
                .withoutPrimaryPublicIpAddress()
                .withPopularLinuxImage(KnownLinuxVirtualMachineImage.UBUNTU_SERVER_16_04_LTS)
                .withRootUsername(userName)
                .withRootPassword(password)
                .withNewDataDisk(10)
                .withNewDataDisk(dataDiskCreatable)
                .withExistingDataDisk(dataDisk)
                .withSize(VirtualMachineSizeTypes.BASIC_A0)
                .create();

        System.out.println("Created a Linux VM (in the same virtual network): " + linuxVM.id());
        AzureUtil.print(linuxVM);

        Date t2 = new Date();
        System.out.println("Created VM: (took " + ((t2.getTime() - t1.getTime()) / 1000) + " seconds) " + linuxVM.id());
        // Print virtual machine details
        AzureUtil.print(linuxVM);

        return linuxVM;
    }



}
