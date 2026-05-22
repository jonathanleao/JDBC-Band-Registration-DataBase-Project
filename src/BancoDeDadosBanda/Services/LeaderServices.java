package BancoDeDadosBanda.Services;

import BancoDeDadosBanda.Domain.Leader;
import BancoDeDadosBanda.Repository.LeaderRepository;

import java.util.List;

public class LeaderServices {

    public static void createLeader(Leader leader) {
        LeaderRepository.createLeader(leader);
    }

    public static void deleteLeader(int id) {
        requireValidId(id);
        LeaderRepository.deleteLeader(id);
    }

    public static void updateLeader(Leader leader) {
        requireValidId(leader.getId());
        LeaderRepository.updateLeader(leader);
    }

    public static void showLeaderMetaData() {
        LeaderRepository.showLeadersDataMeta();
    }

    public static List<Leader> findLeaderByName(String name) {
        return LeaderRepository.findByName(name);
    }

    public static void saveManyLeaders(List<Leader> leaders) {
        if (leaders == null || leaders.isEmpty()){
            throw new IllegalArgumentException("List of leaders cant not be empty");
        }
        LeaderRepository.saveManyLeaderTransactions(leaders);
    }

    public static void requireValidId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("id cant not be null");
        }
    }
}
