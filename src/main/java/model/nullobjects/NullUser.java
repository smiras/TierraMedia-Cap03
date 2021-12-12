package model.nullobjects;

import modelo.Usuario;

public class NullUser extends Usuario {

	public static Usuario build() {
		return new NullUser();
	}
	
	public NullUser() {
		super(0, "", "", 0, 0.0, "");
	}
	
	@Override
	public boolean isNull() {
		return true;
	}
	
}
