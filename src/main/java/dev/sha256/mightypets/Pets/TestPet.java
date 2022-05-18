package dev.sha256.mightypets.Pets;

import dev.sha256.mightypets.MightyPets;
import dev.sha256.mightypets.instance.Pet;

public class TestPet extends Pet {

    private void test() {
        setNBTData("id", getID());
    }

    @Override
    public String getID() {
        return "test";
    }
}
