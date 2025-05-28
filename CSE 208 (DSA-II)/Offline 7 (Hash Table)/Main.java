import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
    public String randomWordGenerator(){
        Random random = new Random();
        StringBuilder randomString;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int count = 0;
        randomString = new StringBuilder("");
        while(count<7){
            int randomNumber = Math.abs(random.nextInt());
            randomString.append(alphabet.charAt(randomNumber % alphabet.length()));
            count++;
        }
        return String.valueOf(randomString);
    }

    public static void main(String[] args) throws Exception{
        BufferedWriter bw1 = new BufferedWriter(new FileWriter("Output1.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("Output2.txt"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("Output3.txt"));
        BufferedWriter bw4 = new BufferedWriter(new FileWriter("Output4.txt"));
        BufferedWriter bw5 = new BufferedWriter(new FileWriter("Output5.txt"));
        BufferedWriter bw6 = new BufferedWriter(new FileWriter("Output6.txt"));
        BufferedWriter bw7 = new BufferedWriter(new FileWriter("Output7.txt"));
        BufferedWriter bw8 = new BufferedWriter(new FileWriter("Output8.txt"));
        BufferedWriter bw9 = new BufferedWriter(new FileWriter("Output9.txt"));
        BufferedWriter bw10 = new BufferedWriter(new FileWriter("Output10.txt"));
        bw1.write("                          Before deletion                 After deletion\n");
        bw1.write("Load Factor               Average Search Time             Average Search Time\n");
        bw2.write("                          Before deletion                                  After deletion\n");
        bw2.write("Load Factor    Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw3.write("                          Before deletion                                  After deletion\n");
        bw3.write("Load Factor    Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw4.write("                          Before deletion                                  After deletion\n");
        bw4.write("Load Factor    Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw5.write("                                 Before deletion                                  After deletion\n");
        bw5.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw6.write("                                 Before deletion                                  After deletion\n");
        bw6.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw7.write("                                 Before deletion                                  After deletion\n");
        bw7.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw8.write("                                 Before deletion                                  After deletion\n");
        bw8.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw9.write("                                 Before deletion                                  After deletion\n");
        bw9.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        bw10.write("                                 Before deletion                                  After deletion\n");
        bw10.write("Method             Average Search Time     Average # of probes     Average Search Time     Average # of probes\n");
        double SCST_4, SCDT_4, LPST_4, LPPC1_4, LPDT_4, LPPC2_4, QPST_4, QPPC1_4, QPDT_4, QPPC2_4, DHST_4, DHPC1_4, DHDT_4, DHPC2_4;
        double SCST_5, SCDT_5, LPST_5, LPPC1_5, LPDT_5, LPPC2_5, QPST_5, QPPC1_5, QPDT_5, QPPC2_5, DHST_5, DHPC1_5, DHDT_5, DHPC2_5;
        double SCST_6, SCDT_6, LPST_6, LPPC1_6, LPDT_6, LPPC2_6, QPST_6, QPPC1_6, QPDT_6, QPPC2_6, DHST_6, DHPC1_6, DHDT_6, DHPC2_6;
        double SCST_7, SCDT_7, LPST_7, LPPC1_7, LPDT_7, LPPC2_7, QPST_7, QPPC1_7, QPDT_7, QPPC2_7, DHST_7, DHPC1_7, DHDT_7, DHPC2_7;
        double SCST_8, SCDT_8, LPST_8, LPPC1_8, LPDT_8, LPPC2_8, QPST_8, QPPC1_8, QPDT_8, QPPC2_8, DHST_8, DHPC1_8, DHDT_8, DHPC2_8;
        double SCST_9, SCDT_9, LPST_9, LPPC1_9, LPDT_9, LPPC2_9, QPST_9, QPPC1_9, QPDT_9, QPPC2_9, DHST_9, DHPC1_9, DHDT_9, DHPC2_9;

        Main main = new Main();
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Set<String> stringSet = new HashSet<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int i=0;i<N;i++){
            stringSet.add(main.randomWordGenerator());
        }
        stringArrayList.addAll(stringSet);
        /*
                Separate Chaining Method
        */
        HashTable hashTable = new HashTable(N, 1);
        ArrayList<String> selectedStrings = new ArrayList<>();
        ArrayList<String> searchStrings = new ArrayList<>();
        ArrayList<String> deletedStrings = new ArrayList<>();
        double loadFactor = 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        int p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        /*
                Load Factor 0.4
        */
        loadFactor = 0.4;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        double startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        double endTime = System.nanoTime();
        double time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_4 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        double time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_4 = time2;
        bw1.write("   0.4                         " + time1 + "                            " + time2 + "\n");
        /*
            Load Factor 0.5
        */
        hashTable.clear();
        selectedStrings.clear();
        searchStrings.clear();
        deletedStrings.clear();
        loadFactor += 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String string : searchStrings) {
            hashTable.search(string);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_5 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_5 = time2;
        bw1.write("   0.5                         " + time1 + "                            " + time2 + "\n");

        /*
            Load Factor 0.6
        */
        hashTable.clear();
        selectedStrings.clear();
        searchStrings.clear();
        deletedStrings.clear();
        loadFactor += 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String string : searchStrings) {
            hashTable.search(string);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_6 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_6 = time2;
        bw1.write("   0.6                         " + time1 + "                            " + time2 + "\n");
        /*
            Load Factor 0.7
        */
        hashTable.clear();
        selectedStrings.clear();
        searchStrings.clear();
        deletedStrings.clear();
        loadFactor += 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String string : searchStrings) {
            hashTable.search(string);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_7 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_7 = time2;
        bw1.write("   0.7                         " + time1 + "                            " + time2 + "\n");
        /*
                Load Factor 0.8
        */
        hashTable.clear();
        selectedStrings.clear();
        searchStrings.clear();
        deletedStrings.clear();
        loadFactor += 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String string : searchStrings) {
            hashTable.search(string);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_8 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_8 = time2;
        bw1.write("   0.8                         " + time1 + "                            " + time2 + "\n");
        /*
                Load Factor 0.9
        */
        hashTable.clear();
        selectedStrings.clear();
        searchStrings.clear();
        deletedStrings.clear();
        loadFactor += 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String string : searchStrings) {
            hashTable.search(string);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCST_9 = time1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        SCDT_9 = time2;
        bw1.write("   0.9                         " + time1 + "                            " + time2 + "\n");
        bw1.close();



        //==================================================================================================================================================================
        /*
                Linear Probing method
        */



        hashTable = new HashTable(N, 2);
        selectedStrings = new ArrayList<>();
        searchStrings = new ArrayList<>();
        deletedStrings = new ArrayList<>();
        loadFactor = 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        /*
                Load Factor 0.4
        */
        loadFactor = 0.4;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_4 = time1;
        double probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_4 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_4 = time2;
        double probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_4 = probe2;
        bw2.write("   0.4              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.5
        */
        loadFactor = 0.5;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_5 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_5 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_5 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_5 = probe2;
        bw2.write("   0.5              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");

        /*
            Load Factor 0.6
        */
        loadFactor = 0.6;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_6 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_6 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_6 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_6 = probe2;
        bw2.write("   0.6              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.7
        */
        loadFactor = 0.7;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_7 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_7 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_7 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_7 = probe2;
        bw2.write("   0.7              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.8
        */
        loadFactor = 0.8;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_8 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_8 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_8 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_8 = probe2;
        bw2.write("   0.8              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.9
        */
        loadFactor = 0.9;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPST_9 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        LPPC1_9 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        LPDT_9 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        LPPC2_9 = probe2;
        bw2.write("   0.9              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        bw2.close();





        //=============================================================================================================================================================

        /*
                Quadratic Probing
        */



        hashTable = new HashTable(N, 3);
        selectedStrings = new ArrayList<>();
        searchStrings = new ArrayList<>();
        deletedStrings = new ArrayList<>();
        loadFactor = 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        /*
                Load Factor 0.4
        */
        loadFactor = 0.4;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_4 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_4 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_4 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_4 = probe2;
        bw3.write("   0.4              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.5
        */
        loadFactor = 0.5;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_5 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_5 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_5 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_5 = probe2;
        bw3.write("   0.5              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");

        /*
            Load Factor 0.6
        */
        loadFactor = 0.6;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_6 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_6 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_6 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_6 = probe2;
        bw3.write("   0.6              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.7
        */
        loadFactor = 0.7;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_7 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_7 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_7 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_7 = probe2;
        bw3.write("   0.7              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.8
        */
        loadFactor = 0.8;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_8 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_8 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_8 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_8 = probe2;
        bw3.write("   0.8              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.9
        */
        loadFactor = 0.9;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPST_9 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        QPPC1_9 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        QPDT_9 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        QPPC2_9 = probe2;
        bw3.write("   0.9              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        bw3.close();


        // ===============================================================================================================================================================

        /*
                        Double Hashing
         */


        hashTable = new HashTable(N, 4);
        selectedStrings = new ArrayList<>();
        searchStrings = new ArrayList<>();
        deletedStrings = new ArrayList<>();
        loadFactor = 0.1;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        /*
                Load Factor 0.4
        */
        loadFactor = 0.4;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_4 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_4 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_4 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_4 = probe2;
        bw4.write("   0.4              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.5
        */
        loadFactor = 0.5;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_5 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_5 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_5 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_5 = probe2;
        bw4.write("   0.5              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");

        /*
            Load Factor 0.6
        */
        loadFactor = 0.6;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_6 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_6 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_6 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_6 = probe2;
        bw4.write("   0.6              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
            Load Factor 0.7
        */
        loadFactor = 0.7;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_7 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_7 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_7 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_7 = probe2;
        bw4.write("   0.7              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.8
        */
        loadFactor = 0.8;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_8 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_8 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_8 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_8 = probe2;
        bw4.write("   0.8              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        /*
                Load Factor 0.9
        */
        loadFactor = 0.9;
        for(int i=0;i<loadFactor*N;i++){
            selectedStrings.add(stringArrayList.get(i));
        }
        for(int i=0;i<selectedStrings.size();i++){
            hashTable.insert(selectedStrings.get(i), i);
        }
        for(int i=0;i<0.1*selectedStrings.size();i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time1 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHST_9 = time1;
        probe1 = hashTable.getProbeCount()/searchStrings.size();
        DHPC1_9 = probe1;
        p = (int) (0.1 * selectedStrings.size());
        for(int i=0;i<p;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            deletedStrings.add(selectedStrings.get(index));
            hashTable.delete(selectedStrings.get(index));
            selectedStrings.remove(selectedStrings.get(index));
        }
        searchStrings.clear();
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % deletedStrings.size();
            searchStrings.add(deletedStrings.get(index));
        }
        for(int i=0;i<p/2;i++){
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % selectedStrings.size();
            searchStrings.add(selectedStrings.get(index));
        }
        startTime = System.nanoTime();
        for (String searchString : searchStrings) {
            hashTable.search(searchString);
        }
        endTime = System.nanoTime();
        time2 = Math.ceil((endTime-startTime)/searchStrings.size());
        DHDT_9 = time2;
        probe2 = hashTable.getProbeCount()/searchStrings.size();
        DHPC2_9 = probe2;
        bw4.write("   0.9              " + time1 + "                     " + probe1 + "                   " + time2 + "                    " + probe2 + "\n");
        bw4.close();
        bw5.write("Separate Chaining            " + SCST_4 + "                 N/A                  " + SCDT_4 + "                     N/A\n");
        bw5.write("Linear Probing               " + LPST_4 + "                 " + LPPC1_4 + "                   " + LPDT_4 + "                   " + LPPC2_4 + "\n");
        bw5.write("Quadratic Probing            " + QPST_4 + "                 " + QPPC1_4 + "                   " + QPDT_4 + "                   " + QPPC2_4 + "\n");
        bw5.write("Double Hashing               " + DHST_4 + "                 " + DHPC1_4 + "                   " + DHDT_4 + "                   " + DHPC2_4 + "\n");
        bw5.close();
        bw6.write("Separate Chaining            " + SCST_5 + "                 N/A                  " + SCDT_5 + "                     N/A\n");
        bw6.write("Linear Probing               " + LPST_5 + "                 " + LPPC1_5 + "                   " + LPDT_5 + "                   " + LPPC2_5 + "\n");
        bw6.write("Quadratic Probing            " + QPST_5 + "                 " + QPPC1_5 + "                   " + QPDT_5 + "                   " + QPPC2_5 + "\n");
        bw6.write("Double Hashing               " + DHST_5 + "                 " + DHPC1_5 + "                   " + DHDT_5 + "                   " + DHPC2_5 + "\n");
        bw6.close();
        bw7.write("Separate Chaining            " + SCST_6 + "                 N/A                  " + SCDT_6 + "                     N/A\n");
        bw7.write("Linear Probing               " + LPST_6 + "                 " + LPPC1_6 + "                   " + LPDT_6 + "                   " + LPPC2_6 + "\n");
        bw7.write("Quadratic Probing            " + QPST_6 + "                 " + QPPC1_6 + "                   " + QPDT_6 + "                   " + QPPC2_6 + "\n");
        bw7.write("Double Hashing               " + DHST_6 + "                 " + DHPC1_6 + "                   " + DHDT_6 + "                   " + DHPC2_6 + "\n");
        bw7.close();
        bw8.write("Separate Chaining            " + SCST_7 + "                 N/A                  " + SCDT_7 + "                     N/A\n");
        bw8.write("Linear Probing               " + LPST_7 + "                 " + LPPC1_7 + "                   " + LPDT_7 + "                   " + LPPC2_7 + "\n");
        bw8.write("Quadratic Probing            " + QPST_7 + "                 " + QPPC1_7 + "                   " + QPDT_7 + "                   " + QPPC2_7 + "\n");
        bw8.write("Double Hashing               " + DHST_7 + "                 " + DHPC1_7 + "                   " + DHDT_7 + "                   " + DHPC2_7 + "\n");
        bw8.close();
        bw9.write("Separate Chaining            " + SCST_8 + "                 N/A                  " + SCDT_8 + "                     N/A\n");
        bw9.write("Linear Probing               " + LPST_8 + "                 " + LPPC1_8 + "                   " + LPDT_8 + "                   " + LPPC2_8 + "\n");
        bw9.write("Quadratic Probing            " + QPST_8 + "                 " + QPPC1_8 + "                   " + QPDT_8 + "                   " + QPPC2_8 + "\n");
        bw9.write("Double Hashing               " + DHST_8 + "                 " + DHPC1_8 + "                   " + DHDT_8 + "                   " + DHPC2_8 + "\n");
        bw9.close();
        bw10.write("Separate Chaining            " + SCST_9 + "                 N/A                  " + SCDT_9 + "                     N/A\n");
        bw10.write("Linear Probing               " + LPST_9 + "                 " + LPPC1_9 + "                   " + LPDT_9 + "                   " + LPPC2_9 + "\n");
        bw10.write("Quadratic Probing            " + QPST_9 + "                 " + QPPC1_9 + "                   " + QPDT_9 + "                   " + QPPC2_9 + "\n");
        bw10.write("Double Hashing               " + DHST_9 + "                 " + DHPC1_9 + "                   " + DHDT_9 + "                   " + DHPC2_9 + "\n");
        bw10.close();
    }
}
