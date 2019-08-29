/**
 * @author  Antoine du HAMEL
 * @section 1.7
 * @exercice 4
 */
package fr.eseo.gpi.exercices.complexe.cartesien;

import java.lang.Math;

public class Complexe {
	protected double reelle=0, imaginaire=0;

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
			this.reelle = arg1;
			this.imaginaire = arg2;
		}
	}

	public double getImaginaire() {
		return imaginaire;
	}

	public void setImaginaire(double imaginaire) {
		this.imaginaire=imaginaire;
	}

	public double getReelle() {
		return reelle;
	}

	public void setReelle(double reelle) {
		this.reelle=reelle;
	}

	public double getModule() {
		return Math.sqrt(reelle*reelle+imaginaire*imaginaire);
	}

	/**
	 * Affecte un nouveau module au nombre complexe en conservant l'argument
	 * @param newModule Le nouveau module à affecter au nombre
	 */
	public void setModule(double newModule) {
		double module=this.getModule();
		if(module==0)
			this.reelle=newModule;
		else
		{
			double factor = newModule/module;
			this.reelle*=factor;
			this.imaginaire*=factor;
		}
	}

	public double getArgument() {
		double theta = Math.acos(this.reelle / this.getModule());
		return Math.signum(this.imaginaire)*theta;
	}

	/**
	 * Affecte un nouvel argument au nombre complexe en conservant son module
	 * @param arg Le nouvel argument à affecter au nombre
	 */
	public void setArgument(double arg) {
		double module = this.getModule();
		this.reelle=module*Math.cos(arg);
		this.imaginaire=module*Math.sin(arg);
	}

	public boolean isNul() {
		return this.reelle==0 && this.imaginaire==0;
	}

	public void plus(Complexe b) {
		this.reelle+=b.getReelle();
		this.imaginaire+=b.getImaginaire();
	}

	public void fois(Complexe b) {
		double x1=this.reelle, y1=this.imaginaire;
		double x2=b.getReelle(), y2=b.getImaginaire();

		this.reelle=x1*x2-y1*y2;
		this.imaginaire=x1*y2+x2*y1;
	}

	public String formeAlgebrique() {
		return this.reelle+" + "+this.imaginaire+"*i";
	}

	public void afficheInfos() {
		System.out.println("Re(Z) = "+this.reelle);
		System.out.println("Im(Z) = "+this.imaginaire);
		System.out.println("|Z| = "+this.getModule());
		if (!this.isNul())
			System.out.println("arg(Z) = "+this.getArgument()+"[2π]");
		System.out.println("Z = "+this.formeAlgebrique());
	}
}