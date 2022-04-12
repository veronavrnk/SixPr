package com.example.sixpr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPrefs;
    private EditText editText;

    public static final String myPrefs = "myprefs";
    public static final String nameKey = "nameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editName);

        // при старте проверяем есть ли в файлах настроек
        // данные по ключу nameKey
        sharedPrefs = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(nameKey)) {
            // если есть, то ставим значение этого ключа в EditText
            editText.setText(sharedPrefs.getString(nameKey, ""));
        }
    }

    // метод для сохранения текста в файл настроек
    public void saveText() {
        // получаем текст
        String editTextValue = editText.getText().toString();

        // получаем доступ к файлу
        SharedPreferences.Editor editor = sharedPrefs.edit();
        // сохраняем по текст из EditText по ключу nameKey
        editor.putString(nameKey, editTextValue);
        editor.apply();
    }

    // метод для получения текста из SharedPreferences по ключу
    public void getText() {
        sharedPrefs = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);
        if (sharedPrefs.contains(nameKey)) {
            editText.setText(sharedPrefs.getString(nameKey, ""));
        }
    }

    // получаем id нажатого виджета и вызываем соответственный ему метод
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.saveBtn:
                // сохраняем текст в файл настроек
                saveText();
                Toast.makeText(this, "Сохранить", Toast.LENGTH_LONG).show();
                break;

            case R.id.clearBtn:
                // очищает текст в виджете EditText
                editText.setText("");
                Toast.makeText(this, "Очистить", Toast.LENGTH_LONG).show();
                break;

            case R.id.getBtn:
                // получаем текст из SharedPreferences
                Toast.makeText(this, "Получить", Toast.LENGTH_LONG).show();
                getText();
                break;
        }
    }
}