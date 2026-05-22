package BancoDeDadosBanda.Domain;

import lombok.ToString;

import java.util.Objects;

@ToString

public class Band {
    private Integer id;
    private int fk_leader;
    private String bandName;
    private int numMembers;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Band band = (Band) o;
        return Objects.equals(id, band.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Integer getId() {
        return id;
    }

    public int getFk_leader() {
        return fk_leader;
    }

    public String getBandName() {
        return bandName;
    }

    public int getNumMembers() {
        return numMembers;
    }

    public static final class BandBuilder {
        private Integer id;
        private int fk_leader;
        private String bandName;
        private int numMembers;

        private BandBuilder() {
        }

        public static BandBuilder builder() {
            return new BandBuilder();
        }

        public BandBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public BandBuilder fk_leader(int fk_leader) {
            this.fk_leader = fk_leader;
            return this;
        }

        public BandBuilder bandName(String bandName) {
            this.bandName = bandName;
            return this;
        }

        public BandBuilder numMembers(int numMembers) {
            this.numMembers = numMembers;
            return this;
        }

        public Band build() {
            Band band = new Band();
            band.fk_leader = this.fk_leader;
            band.id = this.id;
            band.numMembers = this.numMembers;
            band.bandName = this.bandName;
            return band;
        }
    }
}

