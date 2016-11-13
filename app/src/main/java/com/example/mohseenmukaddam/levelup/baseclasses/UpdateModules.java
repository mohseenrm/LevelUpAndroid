package com.example.mohseenmukaddam.levelup.baseclasses;

import java.util.List;

/**
 * Created by mohseenmukaddam on 10/26/16.
 */

public interface UpdateModules {
    public UpdateArgs levelUp();
    public Skillset skillsetUpgrade( Skillset skillset, List<String> args );
}
