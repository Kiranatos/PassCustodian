package net.kiranatos.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import net.kiranatos.HeartOfProgram;
import net.kiranatos.Information;

/**
 * Слушатель ChoiceBox-а длины пароля.
 * Подключается в методе initialize класса DisplayController
 */
public class ChoiceBoxLengthPassListener implements ChangeListener<Number> {
    
    private ChoiceBox cb;

    public ChoiceBoxLengthPassListener(ChoiceBox cb) {
        this.cb = cb;
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        //oldValue, newValue - индексы в коллекции, а не сами значения из коллекции.
        Information.println(oldValue.toString()+" (Индекс старой позиции)");
        Information.println(newValue.toString()+" (Индекс новой позиции)");
        HeartOfProgram.getHeartOfProgramImstance().startEvent(cb, newValue.intValue());
    }

    
    
    
}
