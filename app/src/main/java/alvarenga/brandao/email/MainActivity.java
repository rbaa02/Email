package alvarenga.brandao.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);

        // Definindo a acao click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Encontrando campos de texto pelo ID
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                EditText etTexto = (EditText) findViewById(R.id.etTexto);

                // Obtendo dados digitados pelo usuario
                String email = etEmail.getText().toString();
                String assunto = etAssunto.getText().toString();
                String texto = etTexto.getText().toString();

                // Criando uma intent Implicita
                Intent i = new Intent((Intent.ACTION_SENDTO));
                i.setData(Uri.parse("mailto:"));

                    // Criando lista de emails
                String[] emails = new String[] {email};

                    // Colocando os dados no intent EXTRA
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                // Tentando abrir uma intent
                try{
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                // Pegar o erro caso nao tenha nenhum app
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}