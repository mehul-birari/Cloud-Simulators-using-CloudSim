import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.cloudbus.cloudsim.brokers.DatacenterBroker;
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple;
import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.datacenters.Datacenter;
import org.cloudbus.cloudsim.datacenters.DatacenterSimple;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.hosts.HostSimple;
import org.cloudbus.cloudsim.resources.Pe;
import org.cloudbus.cloudsim.resources.PeSimple;
import org.cloudbus.cloudsim.schedulers.cloudlet.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic;
import org.cloudbus.cloudsim.vms.Vm;
import org.cloudbus.cloudsim.vms.VmSimple;
import org.cloudsimplus.builders.tables.CloudletsTableBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import required.VmAllocationPolicyRR;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *  Round Robin is a scheduling algorithm which is based on the principle of time slices.
 *  Time is divided into multiple slices and each node is given a particular time slice or time interval.
 *  The resources of the service provider are provided to the requesting client on the basis of time slice.
 *
 *  The simulation helps in understanding how the VMs are placed into the Hosts cyclically.
 *  A VM is placed into a Host and the algorithm moves on to the next Host.
 *  Hosts are activated on demand, as virtual machines are needed to be allocated.
 *
 * @author Mehul Birari
 */

public class RoundRobinVmAllocationTimeShared_2DC {

    /* Typesafe Configuration Library used for initializing the parameter values */
    //private static Config parsedConfig = ConfigFactory.parseFile(new File("values.conf"));
    private static Config conf;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinVmAllocationTimeShared_1DC.class.getSimpleName());

    /* Initializing the datacenter characteristics */
    private static int hosts_1;
    private static int host_pes_1;
    private static int mips_1;
    private static int vms_1;
    private static int vm_pes_1;
    private static int cloudlets_1;
    private static int cloudlet_length_1;
    private static int cloudlet_pes_1;

    /* Initializing the VM properties */
    private final long ram_1;
    private final long bw_1;
    private final long storage_1;

    private CloudSim simulation;
    private DatacenterBroker br0;
    private List<Vm> vmList;
    private List<Cloudlet>cloudletList;
    private Datacenter dc0,dc1;

    public static void main(String args[]) {
        LOGGER.info("<======================SIMULATION by MEHUL==========================>");
        new RoundRobinVmAllocationTimeShared_2DC();
    }



    private RoundRobinVmAllocationTimeShared_2DC() {

        try{
            conf = ConfigFactory.load("values.conf");
        }catch(Exception e){
            System.out.println("FILE NOT FOUND!!");
        }


        /* Initializing the datacenter characteristics */
        hosts_1 = conf.getInt("datacenter_properties.hosts");
        host_pes_1 = conf.getInt("datacenter_properties.host_pes");
        mips_1 = conf.getInt("datacenter_properties.mips");
        vms_1 = conf.getInt("datacenter_properties.vms");
        vm_pes_1 = conf.getInt("datacenter_properties.vm_pes");
        cloudlets_1 = conf.getInt("datacenter_properties.cloudlets");
        cloudlet_length_1 = conf.getInt("datacenter_properties.cloudlet_length");
        cloudlet_pes_1 = conf.getInt("datacenter_properties.cloudlet_pes");

        ram_1 = conf.getInt("Vm_properties.ram");
        bw_1 = conf.getInt("Vm_properties.bw");
        storage_1 = conf.getInt("Vm_properties.storage");

        simulation = new CloudSim();

        /* Creation of one data center */
        dc0 = createDatacenter();
        dc1 = createDatacenter();

        /* A broker is created to replicate a cloud customer to manage its Virtual Machines and Cloudlets */
        br0 = new DatacenterBrokerSimple(simulation);

        /* Creating a list to store virtual machines */
        vmList = createVms(new CloudletSchedulerTimeShared());

        /* Creating a list to store cloudlets */
        cloudletList = createCloudlets();

        /* Broker gets the information about demand and supply */
        br0.submitVmList(vmList);
        br0.submitCloudletList(cloudletList);

        /* Simulation starts */
        simulation.start();

        /* After the simulation is finished, the table is built to display the results.
         * Cloudlets are sorted according to the assigned Virtual Machine IDs.
         * Round Robin approach can be observed in the Simulation Results Table.
         */


        List<Cloudlet> finishedCloudlets = br0.getCloudletFinishedList();
        finishedCloudlets.sort(Comparator.comparing(cloudlet -> cloudlet.getVm().getId()));
        new CloudletsTableBuilder(finishedCloudlets).build();  //for building the output simulation table
    }

    /* Creation of Datacenter following the Round Robin Algorithm Implementation */
    private Datacenter createDatacenter() {
        final List<Host> hostList = new ArrayList<>(hosts_1);
        for(int i=0; i < hosts_1; i++) {
            Host host = createHost();
            hostList.add(host);
        }
        return new DatacenterSimple(simulation, hostList, new VmAllocationPolicyRR());
    }

    /* For provisioning Processing Elements for Virtual Machines
     * PeProvisionerSimple is used by default.
     * For provisioning RAM and Bandwidth, ResourceProvisionerSimple is used.
     * For Virtual Machine Scheduling, VMSchedulerSpaceShared is used by default.
     */
    private Host createHost() {
        final List<Pe> peList = new ArrayList<>(host_pes_1); //List of Host unit's Processing Elements
        for(int i=0; i < host_pes_1; i++) {
            peList.add(new PeSimple(mips_1));
        }

        return new HostSimple(ram_1, bw_1, storage_1, peList, false);
    }

    private List<Vm> createVms(CloudletSchedulerTimeShared csts) {
        final List<Vm> list = new ArrayList<>(vms_1);
        for(int i=0; i < vms_1; i++) {
            final Vm vm = new VmSimple(mips_1, vm_pes_1);
            vm.setRam(ram_1).setBw(bw_1).setSize(storage_1).setCloudletScheduler(csts);
            list.add(vm);
        }
        return list;
    }

    private List<Cloudlet> createCloudlets() {
        final List<Cloudlet> list = new ArrayList<>(cloudlets_1);

        final UtilizationModelDynamic utilizationModel = new UtilizationModelDynamic(0.5);

        for(int i=0; i < cloudlets_1; i++) {
            final Cloudlet cloudlet = new CloudletSimple(cloudlet_length_1, cloudlet_pes_1, utilizationModel);
            cloudlet.setSizes(1024);
            list.add(cloudlet);
        }

        return list;
    }
}
