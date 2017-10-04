package templars;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class Main {
	static List<int[]> permutations;
	public Main() {
	}

	public static void printArrayString(String[] array) {
		System.out.println("Array Length: "+array.length);
	    for (String s : array) {
	        System.out.print(s + " ");
	    }
	}
	public static void printArray(int[] array) {
		System.out.print("Key: ");
	    for (int element : array) {
	        System.out.print(element + " ");
	    }
	}
    public String[] split(String text, int chunkSize) { 
        char[] data = text.toCharArray();       
        int len = data.length;
        String[] result = new String[(len+chunkSize-1)/chunkSize];
        int index = 0;
        for (int i=0; i < len; i+=chunkSize) {
            result[index] = new String(data, i, Math.min(chunkSize,len-i));
            index++;
        }
        return result;
    }

	public static void main(String[] args) {
		Main m = new Main();
		String[] cipherArray = new String[200];
		String cipher = null;
		ArrayList<ICombinatoricsVector<Integer>> permutations = new ArrayList<>();
		System.out.println("Hello we are in main");
        String fileName = "resources/cipher.txt";
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                cipher = line;
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
	    cipherArray= m.split(cipher, 7);
        System.out.println("Cipher length: "+ cipher.length());
        System.out.println(cipher);
        printArrayString(cipherArray);
        System.out.println();
        //generate possible keys by taking all permutations of the first 7 digits
        ICombinatoricsVector<Integer> originalVector = Factory.createVector(new Integer[] { 1,2,3,4,5,6,7 });
        Generator<Integer> gen = Factory.createPermutationGenerator(originalVector);
        // Print the result
        for (ICombinatoricsVector<Integer> perm : gen) {
        		permutations.add(perm);
        		System.out.println(perm);
        }
        System.out.println(permutations.size());
        //create a new arraylist of int[] to store the possible keys 
        List<int[]> Perms = new ArrayList<int[]>();
        for(ICombinatoricsVector<Integer> perm: permutations) {
        		List<Integer> list = perm.getVector();
        		int[] array = list.stream().mapToInt(i->i).toArray();
        		Perms.add(array);
        }
        System.out.println(Perms.size());
//        for(int[] per: Perms) {
//            m.solveEverything(per, cipherArray);
//    			System.out.println();
//        }

        //since first 7 letters are ydonoaT you can guess that today is the first 5 letters
        //given that today is the first 5 letters, there are only 4 choices as key:
        // 7526134, 7526143, 7326154, and 7326145
        m.solveEverything(new int[] {7,5,2,6,1,3,4},cipherArray);
    }
	void solveEverything(int[] key, String[] stringArray) {
		String everything = "";
		String element = null;
		for(String s: stringArray) {
			element = solve(key, s);
			everything +=element;
		}
		System.out.println(everything);
	}
	public String solve(int[] key, String cipherElement) {
		String rearrangedString = null;
		char[] characters = cipherElement.toCharArray();
		HashMap<Integer,Character> map = new HashMap<Integer, Character>(); 
		for(int i = 0; i< characters.length; i++) {
			map.put(i+1,characters[i]);
		}
		char[] newchars = new char[8];
		System.out.println(map);
		printArray(key);
		System.out.println();
		System.out.println(map.get(key[0]));
		for(int j = 0; j< key.length; j++) {
			System.out.println(key[j]);
			newchars[j]= map.get(key[j]);
		}
		rearrangedString = String.valueOf(newchars);
//		System.out.println(rearrangedString);
		return rearrangedString;
	}
}
