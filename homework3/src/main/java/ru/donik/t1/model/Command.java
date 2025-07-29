package ru.donik.t1.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Command {

    @NotBlank(message = "Описание команды обязательно!")
    @Size(max = 1000, message = "Длина описания не должна превышать 1000 символов")
    private String description;

    @NotBlank(message = "Имя автора обязательно!")
    @Size(max = 100, message = "Длина имени автора не должна превышать 100 символов")
    private String author;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(?:[-+]\\d{2}:\\d{2}|Z)?", message = "Формат времени некорректен!")
    private String time;

    private Priority priority;
}
