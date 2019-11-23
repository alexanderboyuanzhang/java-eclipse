/**
 * @description: Prints JVM memory utilization statistics
 */
public class GetMemory {

public static String getMemory() {

        String str = "";
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        str += ("##### Heap utilization statistics [MB] #####\n");

        //Print used memory
        str += ("Used Memory:"
                + ((runtime.totalMemory() - runtime.freeMemory()) / mb) + "\n");

        //Print free memory
        str += ("Free Memory:"
                + (runtime.freeMemory() / mb) + "\n");

        //Print total available memory
        str += ("Total available Memory:" + (runtime.totalMemory() / mb) + "\n");

        //Print Maximum available memory
        str += ("Max available Memory:" + (runtime.maxMemory() / mb) + "\n");

        return str;
}

public static void main(String[] args){
        System.out.println(getMemory());
}

}
