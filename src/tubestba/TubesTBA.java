/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubestba;

import java.util.*;
public class TubesTBA {
  
    public static void main(String[] args) {
        
       ArrayList<String> token = new ArrayList();
       String input;
       Stack cek = new Stack();
       Scanner sc = new Scanner(System.in);
       
        System.out.print("Masukkan inputan string : ");
        input = sc.nextLine();
        
        char[] c = input.toCharArray();
        
        for (int i = 0; i < c.length; i++) {
            if(c[i] == 'p'|| c[i] == 'q'|| c[i] == 'r'|| c[i] == 's'){
                    token.add("1");
                    i++;
                
             }else if(c[i] == 'n' && c[i+1] == 'o' && c[i+2] == 't' && (c[i+3] == ' ' || c[i+3] == '(')){
                    token.add("2");
                    i+=2;
            }else if(c[i] == 'a' && c[i+1] == 'n' && c[i+2] == 'd' && (c[i+3] == ' ' || c[i+3] == '(')){
                    token.add("3");
                    i+=2;
            }else if (c[i] == 'o' && c[i+1] == 'r'){
               if(c[i+2] == ' ' || c[i+2] == '('){
                    token.add("4");
                    i++;
                }else{
                    token.add("-1");
                    break;
                }
            }else if(c[i] == 'x' && c[i+1] == 'o' && c[i+2] == 'r' && c[i+3] == ' '){
                    token.add("5");
                     i+=2;
            }else if(c[i] == 'i' && c[i+1] == 'f'){
               if (c[i+2] == 'f'){
                    token.add("8");
                    i+=2;
                }else if (c[i+2] == ' ' || c[i+2] == '('){
                    token.add("6");
                    i++;
                }else{
                    token.add("-1");
                    break;
                }
            }else if(c[i] == 't' && c[i+1] == 'h' && c[i+2] == 'e' && c[i+3] == 'n' && (c[i+4] == ' ' || c[i+4] == '(')){
                    token.add("7");
                    i+=3;
            }else if (c[i] == '(') {
                    token.add("9");
                    i++;
                    
            }else if (c[i] == ')') {
                    token.add("10");
                    i++;
            }else if (c[i] == ' '){
 
            }else{
                    token.add("-1");
                    break;
            }
        }
        System.out.print("Input (String)       : "+input);
        System.out.println();
        System.out.print("Output (Token Lexic) : ");
        for(String a:token){
           if(a == "-1"){
               System.out.print("Error");
           }else{
           System.out.print(a+" ");
          }
        }
        System.out.println();
        int i = 0;
        boolean valid = true;
        cek.push("#");
        cek.push("S");
        
        
        // CFG
        // S -> 1X | 2S | 6S7S | 9S10X
        // X -> 3S | 4S | 5S | 8S | lambda
        
        

        while (cek.peek()!= "#"){
            // DEBUG 
            System.out.println(cek.toString());
            if (i<token.size()) System.out.println("Token now : "+token.get(i));
            System.out.println("Stack now : "+cek.peek());
            // DEBUG END
            
            if(i < token.size() && token.get(i) == "-1") {
                    valid = false;
                    //DEBUG
                    System.out.println("Token error");
                    System.out.println("");
                    //DEBUG END
                    break;
            }
            else if(cek.peek() == "S"){
                if(token.get(i) == "1" || token.get(i) == "2"
                   ||token.get(i) == "6" || token.get(i) == "9"){
                    cek.pop();
                    if(token.get(i) == "1" && i < token.size()){
                        cek.push("X");
                        i++;
                    }else if (token.get(i) == "2" && i < token.size()){
                        cek.push("S");
                        i++;
                    }else if(token.get(i) == "6" && i < token.size()){
                        cek.push("S");
                        cek.push("7");
                        cek.push("S");
                        i++;
                    }else if (token.get(i) == "9" && i < token.size()){
                        cek.push("X");
                        cek.push("10");
                        cek.push("S");
                        i++;
                    }
                }else{
                    valid = false;
                    //DEBUG
                    System.out.println("State S, token diluar 1 2 6 9");
                    System.out.println("");
                    //DEBUG END
                    break;
                }
            }else if(cek.peek() == "X"){
                cek.pop();
                if (i < token.size()){
                    if(token.get(i) == "3" || token.get(i) == "4"||token.get(i) == "5" || token.get(i) == "8"){
                        if(token.get(i) == "3" && i < token.size()){
                            cek.push("S");
                            i++;
                        }else if (token.get(i) == "4" && i < token.size()){
                            cek.push("S");
                            i++;
                        }else if(token.get(i) == "5" && i < token.size()){
                            cek.push("S");
                            i++;
                        }else if(token.get(i) == "8" && i < token.size()){
                            cek.push("S");
                            i++;
                        }
                    }
                }
            }else if(cek.peek() == "7" && i < token.size() && token.get(i)=="7" ){ // THEN
                cek.pop();
                i++;
            }else if (cek.peek() == "10" && i < token.size() && token.get(i) == "10" ){ // " ) "
                cek.pop();
                i++;
            }else{
                valid = false;
                //DEBUG
                System.out.println("Error");
                System.out.println("");
                //DEBUG END
                break;
            }
            
            System.out.println("");
           
            }

        //DEBUG
        System.out.println("Stack Akhir : " + cek.toString());
        //DEBUG END
        if(cek.peek() == "#"){
            cek.pop();
        }
        System.out.println();
        if(cek.isEmpty() && valid){
            System.out.println("VALID");
        }else{
            System.out.println("TIDAK VALID");
        }
        System.out.println();
    }
    
}
