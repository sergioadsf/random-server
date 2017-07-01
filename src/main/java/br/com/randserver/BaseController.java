package br.com.randserver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BaseController<E> {

	protected Response<E> enviarSucesso() {

		return new Response<E>(true);
	}

	protected Response<E> enviarFalha(String msg) {

		return new Response<E>(false, msg);
	}

	protected <T extends Object> Response enviarSucesso(T objetoRetorno) {

		return new Response<T>(true, objetoRetorno);
	}

	protected <T extends Object> T retornar(Class<T> clazz, String str) {

		return new Gson().fromJson(str, clazz);
	}

	protected <T extends Object> T retornar(String str, TypeToken<T> tt) {

		return new Gson().fromJson(str, tt.getType());
	}
}
