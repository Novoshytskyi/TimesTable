package novtsm.com.timestable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private ListView listViewNumbers;
    private ArrayList<Integer> numbers;
    private int max = 20;
    private int min = 1;
    private int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(max);
        listViewNumbers = findViewById(R.id.listViewNumbers);
        numbers = new ArrayList<>();
        // Создание Array -адаптера
//        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, numbers);
        // Добавление адаптера. Установка цвета текста в listView
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, numbers){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.BLUE); // Устоновка цвета по своему усмотрению
                return textView;
            }
        };
        listViewNumbers.setAdapter(arrayAdapter);
        // Слушатель событий изменения SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numbers.clear();
                if(progress < min){
                    seekBar.setProgress(min);
                }
                for(int i = min; i <= count; i++){
                    numbers.add(seekBar.getProgress() * i);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(10);
    }
}