package BancoDeDadosBanda.Domain;

import lombok.ToString;

import java.util.Objects;

@ToString
public class Leader {
    private Integer id;
    private String leaderName;
    private String surname;

    public Integer getId() {
        return id;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Leader leader = (Leader) o;
        return Objects.equals(id, leader.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static final class LeaderBuilder {
        private Integer id;
        private String leaderName;
        private String surname;



        private LeaderBuilder() {
        }

        public static LeaderBuilder builder() {
            return new LeaderBuilder();
        }

        public LeaderBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public LeaderBuilder leaderName(String leaderName) {
            this.leaderName = leaderName;
            return this;
        }

        public LeaderBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public Leader build() {
            Leader leader = new Leader();
            leader.id = this.id;
            leader.surname = this.surname;
            leader.leaderName = this.leaderName;
            return leader;
        }
    }
}
