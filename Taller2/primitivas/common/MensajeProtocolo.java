package primitivas.common;

import java.util.*;
import java.io.*;

@SuppressWarnings("serial")
public class MensajeProtocolo implements Serializable {
	private Primitive primitiva;
	private String mensaje;

	/**
	 * Constructor para una primitiva sin mensaje
	 * 
	 * @param p
	 *            primitiva usada
	 */
	public MensajeProtocolo(Primitive p) {
		/*
		 * Hemos incluido dos assert por la peligrosidad del constructor
		 * recordad que hay que activar expresamente la evaluación de asserts en
		 * la JVM
		 */
		assert p.isCompound() == false;

		this.primitiva = p;
		this.mensaje = "";
	}

	/**
	 * Constructor para una primitiva con mensaje
	 * 
	 * @param p
	 *            primitiva usada
	 * @param m
	 *            mensaje que acompaña la primitiva
	 */
	public MensajeProtocolo(Primitive p, String m) {
		assert p.isCompound() == true && m != null;
		this.primitiva = p;
		this.mensaje = m;
	}

	/**
	 * Getter de la primitiva
	 * 
	 * @return primitiva contenida
	 */
	public Primitive getPrimitive() {
		return this.primitiva;
	}

	/**
	 * Getter del mensaje
	 * 
	 * @return String con el mensaje
	 */
	public String getMessage() {
		return this.mensaje;
	}

	/**
	 * Devuelve el objeto en forma de String
	 */
	public String toString() { /* prettyPrinter de la clase */
		String prettyPrint = "[" + this.primitiva;
		switch (this.primitiva) {
		case HELLO:
			;
		case PUSH:
			;
		case PULL_OK:
			return "[" + this.primitiva + ":" + this.mensaje + "]";
		default:
			return "[" + this.primitiva + "]";
		}
	}
}