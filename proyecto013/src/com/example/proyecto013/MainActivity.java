package com.example.proyecto013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = (EditText) findViewById(R.id.et1);
		String[] archivos = fileList();

		if (existe(archivos, "notas.txt"))
			try {
				InputStreamReader archivo = new InputStreamReader(
						openFileInput("notas.txt"));
				BufferedReader br = new BufferedReader(archivo);
				String linea = br.readLine();
				String todo = "";
				while (linea != null) {
					todo = todo + linea + "\n";
					linea = br.readLine();
				}
				br.close();
				archivo.close();
				et1.setText(todo);
			} catch (IOException e) {
			}

	}

	private boolean existe(String[] archivos, String archbusca) {
		for (int f = 0; f < archivos.length; f++)
			if (archbusca.equals(archivos[f]))
				return true;
		return false;
	}

	public void grabar(View v) {
		try {
			OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
					"notas.txt", Activity.MODE_PRIVATE));
			archivo.write(et1.getText().toString());
			archivo.flush();
			archivo.close();
		} catch (IOException e) {
		}
		Toast t = Toast.makeText(this, "Los datos fueron grabados",
				Toast.LENGTH_SHORT);
		t.show();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
