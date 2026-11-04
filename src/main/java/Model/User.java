package Model;

import it.kamaladafrica.codicefiscale.CodiceFiscale;

public record User(String id, String name, String email, CodiceFiscale CF, Double Credit){}
