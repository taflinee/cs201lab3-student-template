import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          if (traversal1.length() != traversal2.length() || traversal1.length() != traversal3.length()){
               return "Invalid traversals";
          }
          ArrayList<Integer> arr1 = convert(traversal1);
          ArrayList<Integer> arr2 = convert(traversal2);
          ArrayList<Integer> arr3 = convert(traversal3);
          List<Integer> sortedList = new ArrayList<>(arr1);
          Collections.sort(sortedList);
          int[] names = new int[3];
          names[0] = 1;
          names[1] = 2;
          names[2] = 3;
          if (sortedList.equals(arr1)){
          }
          else if (sortedList.equals(arr2)){
               ArrayList<Integer> temp = arr1;
               arr1 = arr2;
               arr2 = temp;
               names[1] = 1;
               names[0] = 2;
          }
          else if (sortedList.equals(arr3)){
               ArrayList<Integer> temp = arr1;
               arr1 = arr3;
               arr3 = temp;
               names[2] = 1;
               names[0] = 3;
          }
          HashMap<Integer,Integer> mp = new HashMap<>();
          for (int i = 0; i < arr1.size(); i++){
               mp.put(arr1.get(i),i);
          }
          if (shorten(mp,arr2,arr3,0,0,0,arr1.size())){
               String result = "";
               if (names[0] == 1){
                    result = "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder";

               }
               else if (names[1] == 1){
                    result = "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder";
               }
               else{
                    result = "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder";
               }
               return result;
          }
          else if (shorten(mp,arr3,arr2,0,0,0,arr1.size())){
               String result = "";
               if (names[0] == 1){
                    result = "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder";
               }
               else if (names[1] == 1){
                    result = "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder";
               }
               else{
                    result = "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder";
               }
               return result;
          }
          else{
               return "Invalid traversals";
          }
          
     }
     public static ArrayList<Integer> convert(String traversal){
          ArrayList<Integer> result = new ArrayList<>();
          for (String value : traversal.split("-")) {
               result.add(Integer.parseInt(value));
          }
          return result;
     }
     public static boolean shorten(HashMap<Integer,Integer> in, ArrayList<Integer> pre, ArrayList<Integer> post, int p, int i, int q, int size){
          if (size == 0){
               return true;
          }
          int root = pre.get(i);
          if (root != post.get(q+size-1)){
               return false;
          }

          if (in.get(root) == null || in.get(root) >= p+size || in.get(root) < p){
               return false;
          }
          int mid = in.get(root);
          if (!shorten(in,pre,post,p,i+1,q,mid-p)){
               return false;
          }
          if (!shorten(in, pre, post, mid+1, i+(mid-p)+1, q+mid-p, size-(mid-p)-1)){
               return false;
          }
          return true;
     }
}