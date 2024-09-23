package br.com.helioarai.fazerhotdog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText etNome ;
    RadioGroup rgProteina ;
    CheckBox cbCatchup ;
    CheckBox cbMostarda ;
    CheckBox cbMaionese ;
    Switch swMilho ;
    Switch swErvilha ;
    Switch swQueijoRalado ;
    Button btPedido ;
    TextView tvSaidaPedido ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupViews();

        setupuListeners();


    } // fim do onCreate

    private void setupuListeners () {
        btPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cliente = nomeCliente();
                String proteinaSelecionada = proteina();
                String molhosSelecionados = molhos();
                String acompanhamentosSelecionados = acompanhamentos();

                String pedido = cliente  + "\n\n" +
                        proteinaSelecionada + "\n\n" +
                        molhosSelecionados + "\n\n" +
                        acompanhamentosSelecionados ;

                tvSaidaPedido.setText(pedido);



                //Toast.makeText(MainActivity.this, cliente , Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String nomeCliente () {
        String cliente = etNome.getText().toString();
        if (cliente.isEmpty()) {
            cliente="Não informou nome";
        }
        return "Cliente : " + cliente ;
    }

    private String proteina(){
        int proteinaSelecionadaId = rgProteina.getCheckedRadioButtonId();
        String proteinaSelecionadaString = "Nenhuma";

        if (proteinaSelecionadaId != -1) {
            RadioButton proteinaSelecionada = findViewById(proteinaSelecionadaId);

            if (proteinaSelecionada.toString().contains("linguica")) {
                proteinaSelecionadaString = "Linguica";
            }

            if (proteinaSelecionada.toString().contains("salsicha")) {
                proteinaSelecionadaString = "Salsicha";
            }
        }

        return "Proteina : " + proteinaSelecionadaString ;

    }

    private String molhos () {
        List<String> molhosSelecionados = new ArrayList<>();

        if (cbCatchup.isChecked()){
            molhosSelecionados.add("Catchup");
        }

        if (cbMostarda.isChecked()){
            molhosSelecionados.add("Mostarda");
        }

        if (cbMaionese.isChecked()){
            molhosSelecionados.add("Maionese");
        }

        if (molhosSelecionados.isEmpty()){
            return"Sem molho. ";
        } else {
            return "Molhos selecionados : \n - " + String.join(", ", molhosSelecionados) + ".";
        }


    }

    private String acompanhamentos () {
        List<String> acompanhamentosSelecionados = new ArrayList<>();

        if (swMilho.isChecked()){
            acompanhamentosSelecionados.add("Milho");
        }

        if (swErvilha.isChecked()){
            acompanhamentosSelecionados.add("Ervilha");
        }

        if (swQueijoRalado.isChecked()){
            acompanhamentosSelecionados.add("Queijo Ralado");
        }

        if (acompanhamentosSelecionados.isEmpty()){
            return"Sem acompanhamento. ";
        } else {
            return "Acompanhamentos : \n - " + String.join(", ", acompanhamentosSelecionados) + ".";
        }


    }


    private void setupViews() {
        etNome = findViewById(R.id.et_nome);
        rgProteina = findViewById(R.id.rb_proteina) ;
        cbCatchup = findViewById(R.id.cb_catchup) ;
        cbMostarda  = findViewById(R.id.cb_mostarda) ;
        cbMaionese = findViewById(R.id.cb_maionese) ;
        swMilho = findViewById(R.id.sw_milho) ;
        swErvilha = findViewById(R.id.sw_ervilha) ;
        swQueijoRalado =findViewById(R.id.sw_queijo_ralado);
        btPedido  = findViewById(R.id.bt_pedido) ;
        tvSaidaPedido = findViewById(R.id.tv_saida_pedido) ;
    }
}