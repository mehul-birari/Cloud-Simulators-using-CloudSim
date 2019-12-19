import com.typesafe.config.Config;



public final class RoundRobinVmAllocationTestUtil {

    /* Typesafe Configuration Library used for initializing the parameter values */
    public static Config conf;

    // private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinVmAllocation_1DC.class.getSimpleName());

    /* Initializing the datacenter characteristics */
    public static int hosts = 8;
    public static int host_pes = 8;
    public static int mips = 2000;
    public static int vms = 16;
    public static int vm_pes = 2;
    public static int cloudlets = 16;
    public static int cloudlet_length = 10000;
    public static int cloudlet_pes = 2;
    public static int cloudlet_size = 1024;

    public static long ram = 4096;
    public static long bw = 10000;
    public static long storage = 1000000;


    public RoundRobinVmAllocationTestUtil() { /* */ }


}







