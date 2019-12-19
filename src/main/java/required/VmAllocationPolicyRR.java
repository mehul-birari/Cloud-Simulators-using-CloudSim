package required;


import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicy;
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyAbstract;
import org.cloudbus.cloudsim.hosts.Host;
import org.cloudbus.cloudsim.vms.Vm;

import java.util.List;
import java.util.Optional;

/**
 * Round Robin is a simple, most widely used scheduling algorithm,
 * specially designed for time-sharing systems. All runnable processes
 * are kept in a circular fashion. The VM scheduler goes around the queue,
 * allocating the VM to each process for a time interval of one quantum.
 *
 * @author Mehul Birari
 */
public class VmAllocationPolicyRR extends VmAllocationPolicyAbstract implements VmAllocationPolicy {
    /**
     * The index of the last host used to place a VM.
     */
    private int lastHostIndex;

    @Override
    protected Optional<Host> defaultFindHostForVm(final Vm vm) {
        final List<Host> hostList = getHostList();
        /* The for loop just defines the maximum number of Hosts to try.
         * When a suitable Host is found, the method returns immediately. */
        final int maxTries = hostList.size();
        for (int i = 0; i < maxTries; i++) {
            final Host host = hostList.get(lastHostIndex);
            lastHostIndex = ++lastHostIndex % hostList.size();
            if (host.isSuitableForVm(vm)) {
                return Optional.of(host);
            }
        }

        return Optional.empty();
    }
}