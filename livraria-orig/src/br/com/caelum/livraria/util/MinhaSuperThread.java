package br.com.caelum.livraria.util;

public class MinhaSuperThread implements Runnable {

	private int i = 0;
	
	@Override
	public void run() {
		while (i < 10) {
			System.out.println("EU SOU UMA THEAD RUNNABLE");
			i++;
			
			if (i == 5) {
				try {
					Thread.sleep(5000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}
