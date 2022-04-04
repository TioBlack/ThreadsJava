package Threads;



class Global {
    public static int M = 20;
    public static int caixas_no_deposito = 0;
}



class Empacotador extends Thread {
    int id, te, quantidade_empacotadores, i;
    Empacotador[] empacotadores = new Empacotador[quantidade_empacotadores];
    
    
    boolean empacotando, armazenando;
    long time2, j;
    
    
    
    
    public Empacotador(int empacotador, int tempo_empacotamento){
        this.id = empacotador;
        this.te = tempo_empacotamento * 1000;
        
        
    }
    
    public void empacotadores() {
    	for (i = 0; i < quantidade_empacotadores; i++) {
    		
    	}
    }
    

    public void run() {
        while (true){
            empacotando();
            armazenando();
            
        }
    }

    public void empacotando(){
        if (Global.caixas_no_deposito < Global.M){
            time2 = System.currentTimeMillis();
            j = 0;
            System.out.println("Empacotador " + id + " Empacotando");
            while (System.currentTimeMillis() - time2 < te){
                j++;
                armazenando = true;
            }
            System.out.println("Empacotador "+ id + " Empacotou");
        }
    }

    public void armazenando(){
        if (Global.caixas_no_deposito < Global.M){
            while (armazenando){
                Global.caixas_no_deposito++;
                System.out.println("Depósito: "+ Global.caixas_no_deposito);
                armazenando = false;
            }
        }
    }

}


class Trem extends Thread {
	int N, tv, caixas_no_trem;
    boolean carregando, descarregando, viajando_para_A, viajando_para_B;
    long time, k;

    public Trem(int limite_caixas, int tempo_viagem){
        this.N = limite_caixas;
        this.tv = tempo_viagem * 1000;
    }
    
    
    public void run() {
        while (true){
            carregando();
            viajando_para_B();
            descarregando();
            viajando_para_A();
        }
    }


    public void carregando(){
        if (Global.caixas_no_deposito < N){
            carregando = false;
            time = System.currentTimeMillis();
            while (System.currentTimeMillis() - time < 1500) {
            	
            }
            System.out.println("TREM DORMINDO");		
            
        }else{
            carregando = true;
            System.out.println("CARREGANDO TREM");
            
        }
        while (carregando == true){
            Global.caixas_no_deposito--;
            caixas_no_trem++;
            System.out.println("caixas no depósito:"+ Global.caixas_no_deposito);
            System.out.println("caixas no trem:"+ caixas_no_trem);
            
            if (caixas_no_trem == N){
                carregando = false;
                viajando_para_B = true;
            }
        }
    }

    public void descarregando(){
        while (descarregando == true){
            System.out.println("DESCARREGANDO");
            if (caixas_no_trem > 0){
                caixas_no_trem--;
                System.out.println("caixas no trem:"+ caixas_no_trem);
            }else{
                descarregando = false;
                viajando_para_A = true;
            }
        }
    }

    public void viajando_para_B(){
        if (viajando_para_B == true){
            time = System.currentTimeMillis();
            k = 0;
            System.out.println("VIAJANDO PARA B");
            while (System.currentTimeMillis() - time < tv){
                k++;
            }
            System.out.println("CHEGOU EM B");
            descarregando = true;
            viajando_para_B = false;
        }
        
    }

    public void viajando_para_A(){
        if (viajando_para_A == true){
            time = System.currentTimeMillis();
            k = 0;
            System.out.println("VIAJANDO PARA A");
            while (System.currentTimeMillis() - time < tv){
                k++;
            }
            System.out.println("CHEGOU EM A");
            carregando = true;
            viajando_para_A = false;
        }
    }
}







public class Executar {
	public static void main(String[] agrs) {
		
		Trem trem = new Trem(5, 6);
		Empacotador emp = new Empacotador(1, 3);
		
		
		
		
		emp.start();
		trem.start();
		
	}
}
