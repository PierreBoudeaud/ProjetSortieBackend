package fr.eni.projetsortie.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InscriptionId implements Serializable {
    private int sortieId;

    private int participantId;

    private InscriptionId() {}

    public InscriptionId(
            int sortieId,
            int participantId) {
        this.sortieId = sortieId;
        this.participantId = participantId;
    }

    //Getters omitted for brevity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        InscriptionId that = (InscriptionId) o;
        return Objects.equals(sortieId, that.sortieId) &&
                Objects.equals(participantId, that.participantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortieId, participantId);
    }
}