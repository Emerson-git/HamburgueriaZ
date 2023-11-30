package emerson.mobile.hamburgueriaz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final float PRECO_BASE = 20.0f;
    private static final float PRECO_BACON = 2.0f;
    private static final float PRECO_QUEIJO = 2.0f;
    private static final float PRECO_ONION = 3.0f;
    int qtd = 0;
    EditText edtNomeCliente;
    TextView qtdText, resumoText;

    String resumoPedido = "";

    CheckBox checkBacon, checkQueijo, checkOnion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        qtdText = findViewById(R.id.incremento);
        edtNomeCliente = findViewById(R.id.editTextText);
        checkBacon = findViewById(R.id.checkBox);
        checkQueijo = findViewById(R.id.checkBox2);
        checkOnion = findViewById(R.id.checkBox3);
        resumoText = findViewById(R.id.textView5);
    }


    public void somar(View view){
        qtd++;
        qtdText.setText( qtd + "");
        resumoPedido();
    }

    public void subtrair(View view){
        if ( qtd > 0){
            qtd--;
            qtdText.setText( qtd + "");
            resumoPedido();
        }
    }

    @SuppressLint("DefaultLocale")
    public void resumoPedido(){
        
        Float valorTotal = PRECO_BASE;
        
        String nomeDoCliente = edtNomeCliente.getText().toString();

        Boolean temBacon, temQueijo, temOnion;

        temBacon = checkBacon.isChecked();
        temQueijo = checkQueijo.isChecked();
        temOnion = checkOnion.isChecked();
        
        if ( temBacon ){
            valorTotal += PRECO_BACON;
        }

        if ( temQueijo ){
            valorTotal += PRECO_QUEIJO;
        }

        if ( temOnion ){
            valorTotal += PRECO_ONION;
        }

        valorTotal = valorTotal * qtd;
        

         resumoPedido = String.format(
                "Nome do cliente: %s\n" +
                "Tem Bacon? %s\n" +
                "Tem Queijo? %s\n" +
                "Tem Onion Rings? %s\n" +
                "Quantidade: %d\n" +
                "Preço final: R$ %.2f", nomeDoCliente, temBacon ? "Sim" : "Não", temQueijo ? "Sim" : "Não", temOnion ? "Sim" : "Não", qtd, valorTotal);


        resumoText.setText(resumoPedido);

    }

    public void enviarEmail(View view) {
        String[] destinatarios = {"destinatario@example.com"};
        String assunto = "Pedido de " + edtNomeCliente.getText().toString();
        String corpo = resumoPedido;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // Apenas aplicativos de e-mail devem lidar com isso
        intent.putExtra(Intent.EXTRA_EMAIL, destinatarios);
        intent.putExtra(Intent.EXTRA_SUBJECT, assunto);
        intent.putExtra(Intent.EXTRA_TEXT, corpo);

        startActivity(intent);
    }

}