import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

//import org.apache.commons.codec.binary.StringUtils;

/**
 * @author jonas_trevisol
 * 
 */


public class ComparacaoArquivos {
	private static String fonte = "SCPC";
	
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
//        // SCPC
//        HashSet<String> hashPA = new ComparacaoArquivos().extrairHash("C:\\Dev\\PA-equalização\\serasa\\SERASA-PA.csv");
//        HashSet<String> hashfonte = new ComparacaoArquivos().extrairHash("C:\\Dev\\PA-equalização\\serasa\\SERASA-FONTE.txt");
//
//        comparacao(hashPA, hashfonte, "C:\\Dev\\PA-equalização\\serasa\\enviar-para-fonte.txt");
//        comparacao(hashfonte, hashPA, "C:\\Dev\\PA-equalização\\serasa\\incluir-PA-SICREDI.txt");
        

        // SCPC
        HashSet<String> hashPA = new ComparacaoArquivos().extrairHash("C:\\Users\\rodrigof\\Desktop\\testejonas\\PA-SCPC.csv");
        HashSet<String> hashfonte = new ComparacaoArquivos().extrairHash("C:\\Users\\rodrigof\\Desktop\\testejonas\\FONTE-SCPC.txt");

        System.out.println("Adicionar na fonte: ");
        comparacao(hashPA, hashfonte, "C:\\Users\\rodrigof\\Desktop\\testejonas\\adicionar-na-fonte - " + fonte + ".txt");
        System.out.println("Adicionar no PA: ");
        comparacao(hashfonte, hashPA, "C:\\Users\\rodrigof\\Desktop\\testejonas\\adicionar-no-PA-SICREDI.txt");
    }

    /**
     * @param hashPA
     * @param hashfonte
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private static void comparacao(HashSet<String> hashPA, HashSet<String> hashfonte, String fileName)
            throws FileNotFoundException, UnsupportedEncodingException {

        int contemSomenteFonte = 0;
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for (String i : hashPA) {
            if (i != null && !i.equals("") && !hashfonte.contains(i)) {
                contemSomenteFonte++;
                writer.println(i);
                System.out.println(i);
            }
        }
        writer.close();

        System.out.println("diferenca entre " + fileName + " : " + contemSomenteFonte);

    }

    /**
     * 
     */
    private HashSet<String> extrairHash(String fileName) {
        System.out.println("------------- " + fileName);
        HashSet<String> hash = new HashSet<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
            String line = null;
            int count = 0;

            String doc = "";
            while ((line = br.readLine()) != null) {
                try {
                    line = line.replaceAll("\"", "");
                    
                    if(line.length() == 14){
                        doc = line.substring(0,8);
                    }else{
                        doc = fill(line, 11);
                    }
                    
                    if (!hash.add(doc)) {
                        System.out.println(fileName + " --> valor --> " + doc);
                    }
                    count++;
                } catch (NumberFormatException e) {
                    System.err.println(line + " " + e.getMessage());
                }
            }
            
            System.out.println("linhas --> " + count);
            System.out.println("hash   --> " + hash.size());
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hash;
    }

    public static String fill(String text, int size) {
        while (text.length() < size) {
            text = "0" + text;
        }
        return text;
    }
}
