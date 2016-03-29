/**
 * @author  Antoine du HAMEL
 * @section 1.7
 * @exercice 4
 */
package fr.eseo.gpi.exercices.complexe.polaire;

import java.lang.Math;

public class Complexe {
	protected double module=0, argument=0;

	public Complexe() {}

	/**
	 * Insentie un nombre complexe avec un module et un argument en polaire ou une partie réelle et une partie imaginaire (non polaire)
	 * @param  isPolar Indique si la définition du nombre se fait en polaire ou non
	 * @param  arg1    Le module en polaire, la partie réelle sinon
	 * @param  arg2    L'argument en polaire, la partie imaginaire sinon
	 */
	public Complexe (boolean isPolar, double arg1, double arg2) {
		if (isPolar)
		{
			this.setModule(arg1);
			this.setArgument(arg2);
		}
		else
		{
			this.setReelle(arg1);
			this.setImaginaire(arg2);
		}
	}

	public double getImaginaire() {
		return this.module*Math.sin(this.argument);
	}

	public void setImaginaire(double imaginaire) {
		double reelle=this.getReelle();
		double theta = Math.acos(reelle / this.getModule());
		this.module=Math.sqrt(imaginaire*imaginaire+reelle*reelle);
		this.argument= Math.signum(imaginaire)*theta;
	}

	public double getReelle() {
		return this.module*Math.cos(this.argument);
	}

	public void setReelle(double reelle) {
		double imaginaire=this.getImaginaire();
		double theta = Math.acos(reelle / this.getModule());
		this.module=Math.sqrt(imaginaire*imaginaire+reelle*reelle);
		this.argument=Math.signum(this.getImaginaire())*theta;
	}

	public double getModule() {
		return this.module;
	}

	/**
	 * Affecte un nouveau module au nombre complexe en conservant l'argument
	 * @param newModule Le nouveau module à affecter au nombre
	 */
	public void setModule(double newModule) {
		this.module=newModule;
	}

	public double getArgument() {
		return this.argument;
	}

	/**
	 * Affecte un nouvel argument au nombre complexe en conservant son module
	 * @param arg Le nouvel argument à affecter au nombre
	 */
	public void setArgument(double arg) {
		this.argument=arg;
	}

	public boolean isNul() {
		return this.module==0;
	}

	public void plus(Complexe b) {
		this.reelle+=b.getReelle();
		this.imaginaire+=b.getImaginaire();
	}

	public void fois(Complexe b) {
		this.argument+=b.getArgument();
		this.module*=b.getModule();
	}

	public String formeAlgebrique() {
		return this.getReelle()+" + "+this.getImaginaire()+"*i";
	}
	
	public String toString() {
		return this.module+" * e^"+this.argument;
	}

	public void afficheInfos() {
		System.out.println("Re(Z) = "+this.getReelle());
		System.out.println("Im(Z) = "+this.getImaginaire());
		System.out.println("|Z| = "+this.getModule());
		if (!this.isNul())
			System.out.println("arg(Z) = "+this.getArgument()+"[2π]");
		System.out.println("Z = "+this.formeAlgebrique());
	}
}