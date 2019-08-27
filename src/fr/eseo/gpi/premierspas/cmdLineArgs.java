package fr.eseo.gpi.premierspas;

class cmdLineArgs {
	public static void main (String[]args){
		System.out.println("Hello");
		if(args.length==0){
			System.out.println("No input arguments");

		} else if (args.length==1){
			System.out.println("Single commande line argument :");
			System.out.println(args[0]);
		} else {
			System.out.println("Command line arguments (" + args.length +") :");
			for (int i=0;i<args.length;i++){
				System.out.println("Arg nb" + (i+1) + " " + args[i]);
			}
		}
	}
}