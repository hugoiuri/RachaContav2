package br.com.hugo.rachacontav2.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.com.hugo.rachacontav2.R;
import br.com.hugo.rachacontav2.model.Pessoa;

public class AddPessoaActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgFoto;
    private TextInputEditText edtNome;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pessoa);

        imgFoto = findViewById(R.id.imgAddFotoId);
        edtNome = findViewById(R.id.edtNomeId);
    }

    // PROCEDIMENTO PARA ADICIONAR UMA FOTO CAPTURADA PELA CAMERA DO ANDROID
    public void onClickAddFoto(View view){

        PackageManager pm = this.getPackageManager();

        // PRECISAMOS VERIFICAR SE TEMOS ACESSO A CAMERA OU NÃO
        if(pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){

            // APÓS ACESSAR A CAMÊRA DEVEMOS VALIDAR SE O USUÁRIO INFORMOU AS PERMISSÕES
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                imgFoto.setEnabled(false);
                ActivityCompat.requestPermissions(this, new  String[]{
                        Manifest.permission.CAMERA    },0
                );
            }

            // REALIZA A ABERTURA DA CAMERA E CAPTURA A FOTO
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }

        }else{
            Toast.makeText(this, "Não é possível usar a camêra!", Toast.LENGTH_LONG).show();
        }
    }

    // PROCEDIMENTO PARA RECUPERAR O RESULTADO DO ACTIVITY DE RECUPERAR FOTO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgFoto.setImageBitmap(imageBitmap);
        }
    }

    // PROCEDIMENTO PARA EXECUTAR A ACAO DO BOTÃO
    public void onClickAdicionar(View view){

        String nome = edtNome.getText().toString();

        if(nome == null || nome.equals("")){
            Toast.makeText(this, "Obrigatório informar o nome!", Toast.LENGTH_LONG).show();
        }else{

            Pessoa pessoa = new Pessoa();
            pessoa.setName(nome);

            // PRECISA CONVERTER A IMAGEM PARA BYTES
            if(imageBitmap != null){
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
                byte[] img = saida.toByteArray();
                pessoa.setFoto(img);
            }

            // ADICIONA A PESSOA COMO RESULTADO DO ACTIVITY
            Intent intent = new Intent();
            intent.putExtra("pessoa", pessoa);
            setResult(1, intent);
            finish();
        }

    }
}
