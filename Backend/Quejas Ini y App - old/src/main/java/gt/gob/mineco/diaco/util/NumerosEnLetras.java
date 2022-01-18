/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.gob.mineco.diaco.util;

  
public class NumerosEnLetras {
    private int flag;
    public String num;
    public String numLetra;
    public String numLetras;
    public String numLetram;
    public String numLetradm;
    public String numLetracm;
    public String numLetramm;
    public String numLetradmm;
    public NumerosEnLetras(){
        /*constructor vacio*/
    }
    private String unidad(int pNumero){
        switch (pNumero){
            case 9:
                num = "nueve";
                break;
            case 8:
                num = "ocho";
                break;
            case 7:
                num = "siete";
                break;
            case 6:
                num = "seis";
                break;
            case 5:
                num = "cinco";
                break;
            case 4:
                num = "cuatro";
                break;
            case 3:
                num = "tres";
                break;
            case 2:
                num = "dos";
                break;
            case 1:
                if (flag == 0){
                    num = "uno";
                }else{
                    num = "un";
                }
                break;
            case 0:
                num = "";
                break;
        }
        return num;
    }
    
    private String decena(int pNumero){
        int numero = pNumero;
        if (numero >= 90 && numero <= 99)
        {
            numLetra = "noventa ";
            if (numero > 90){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 90));
            }
        }
        else if (numero >= 80 && numero <= 89)
        {
            numLetra = "ochenta ";
            if (numero > 80){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 80));
            }
        }
        else if (numero >= 70 && numero <= 79)
        {
            numLetra = "setenta ";
            if (numero > 70){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 70));
            }
        }
        else if (numero >= 60 && numero <= 69)
        {
            numLetra = "sesenta ";
            if (numero > 60){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 60));
            }
        }
        else if (numero >= 50 && numero <= 59)
        {
            numLetra = "cincuenta ";
            if (numero > 50){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 50));
            }
        }
        else if (numero >= 40 && numero <= 49)
        {
            numLetra = "cuarenta ";
            if (numero > 40){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 40));
            }
        }
        else if (numero >= 30 && numero <= 39)
        {
            numLetra = "treinta ";
            if (numero > 30){
                numLetra = numLetra.concat("y ").concat(unidad(numero - 30));
            }
        }
        else if (numero >= 20 && numero <= 29)
        {
            if (numero == 20){
                numLetra = "veinte ";
            }else{
                numLetra = "veinti".concat(unidad(numero - 20));
            }
        }
        else if (numero >= 10 && numero <= 19)
        {
            switch (numero){
                case 10:
                    numLetra = "diez ";
                    break;
                case 11:
                    numLetra = "once ";
                    break;
                case 12:
                    numLetra = "doce ";
                    break;
                case 13:
                    numLetra = "trece ";
                    break;
                case 14:
                    numLetra = "catorce ";
                    break;
                case 15:
                    numLetra = "quince ";
                    break;
                case 16:
                    numLetra = "dieciseis ";
                    break;
                case 17:
                    numLetra = "diecisiete ";
                    break;
                case 18:
                    numLetra = "dieciocho ";
                    break;
                case 19:
                    numLetra = "diecinueve ";
                    break;
            }	
        }
        else{
            numLetra = unidad(numero);
        }
        return numLetra;
    }	
    
    private String centena(int pNumero){
        int numero = pNumero;
        if (numero >= 100)
        {
            if (numero >= 900 && numero <= 999)
            {
                numLetra = "novecientos ";
                if (numero > 900){
                    numLetra = numLetra.concat(decena(numero - 900));
                }
            }
            else if (numero >= 800 && numero <= 899)
            {
                numLetra = "ochocientos ";
                if (numero > 800){
                    numLetra = numLetra.concat(decena(numero - 800));
                }
            }
            else if (numero >= 700 && numero <= 799)
            {
                numLetra = "setecientos ";
                if (numero > 700){
                    numLetra = numLetra.concat(decena(numero - 700));
                }
            }
            else if (numero >= 600 && numero <= 699)
            {
                numLetra = "seiscientos ";
                if (numero > 600){
                    numLetra = numLetra.concat(decena(numero - 600));
                }
            }
            else if (numero >= 500 && numero <= 599)
            {
                numLetra = "quinientos ";
                if (numero > 500){
                    numLetra = numLetra.concat(decena(numero - 500));
                }
            }
            else if (numero >= 400 && numero <= 499)
            {
                numLetra = "cuatrocientos ";
                if (numero > 400){
                    numLetra = numLetra.concat(decena(numero - 400));
                }
            }
            else if (numero >= 300 && numero <= 399)
            {
                numLetra = "trescientos ";
                if (numero > 300){
                    numLetra = numLetra.concat(decena(numero - 300));
                }
            }
            else if (numero >= 200 && numero <= 299)
            {
                numLetra = "doscientos ";
                if (numero > 200){
                    numLetra = numLetra.concat(decena(numero - 200));
                }
            }
            else if (numero >= 100 && numero <= 199)
            {
                if (numero == 100){
                    numLetra = "cien ";
                }else{
                    numLetra = "ciento ".concat(decena(numero - 100));
                }
            }
        }
        else{
            numLetra = decena(numero);
        }
        return numLetra;	
    }	
    
    private String miles(int pNumero){
        int numero = pNumero;
        if (numero >= 1000 && numero <2000){
            numLetram = ("mil ").concat(centena(numero%1000));
        }
        if (numero >= 2000 && numero <10000){
            flag=1;
            numLetram = unidad(numero/1000).concat(" mil ").concat(centena(numero%1000));
        }
        if (numero < 1000){
            numLetram = centena(numero);
        }
        return numLetram;
    }
    
    
    private String decmiles(int pNumero){
        int numero = pNumero;
        if (numero == 10000){
            numLetradm = "diez mil";
        }
        if (numero > 10000 && numero <20000){
            flag=1;
            numLetradm = decena(numero/1000).concat("mil ").concat(centena(numero%1000));		
        }
        if (numero >= 20000 && numero <100000){
            flag=1;
            numLetradm = decena(numero/1000).concat(" mil ").concat(miles(numero%1000));		
        }
        if (numero < 10000){
            numLetradm = miles(numero);
        }
        return numLetradm;
    }		
        
    private String cienmiles(int pNumero){
        int numero = pNumero;
        if (numero == 100000){
            numLetracm = "cien mil";
        }
        if (numero >= 100000 && numero <1000000){
            flag=1;
            numLetracm = centena(numero/1000).concat(" mil ").concat(centena(numero%1000));		
        }
        if (numero < 100000){
            numLetracm = decmiles(numero);
        }
        return numLetracm;
    }	
    
    private String millon(int pNumero){
        int numero = pNumero;
        if (numero >= 1000000 && numero <2000000){
            flag=1;
            numLetramm = ("Un millon ").concat(cienmiles(numero%1000000));
        }
        if (numero >= 2000000 && numero <10000000){
            flag=1;
            numLetramm = unidad(numero/1000000).concat(" millones ").concat(cienmiles(numero%1000000));
        }
        if (numero < 1000000){
            numLetramm = cienmiles(numero);
        }
        return numLetramm;
    }		
        
    private String decmillon(int pNumero){
        int numero = pNumero;
        if (numero == 10000000){
            numLetradmm = "diez millones";
        }
        if (numero > 10000000 && numero <20000000){
            flag=1;
            numLetradmm = decena(numero/1000000).concat("millones ").concat(cienmiles(numero%1000000));		
        }
        if (numero >= 20000000 && numero <100000000){
            flag=1;
            numLetradmm = decena(numero/1000000).concat(" milllones ").concat(millon(numero%1000000));		
        }
        if (numero < 10000000){
            numLetradmm = millon(numero);
        }
        return numLetradmm;
    }		
        
    public  String  convertir(int pNumero){
        int numero = pNumero;
        numLetras = decmillon(numero);
        return numLetras;
    } 	
}