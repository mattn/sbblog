package io.github.mattn.sbblog.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EntryForm {
    @NotNull
    @Size(min = 1, max = 256)
    private String title;

    @NotNull
    @Size(min = 1, max = 4096)
    private String body;
}
