package BancoDeDadosBanda.Services;

import BancoDeDadosBanda.Domain.Band;
import BancoDeDadosBanda.Repository.BandRepository;

import java.util.List;

public class BandServices {
    public static void createBand(Band band) {
        requireValidId(band.getFk_leader());
        BandRepository.createBand(band);

    }
    public static void deleteBand(int id){
        requireValidId(id);
        BandRepository.deleteBand(id);
    }

    public static void updateBand(Band band){
        requireValidId(band.getId());
        BandRepository.updateBand(band);
    }
    public static void showBandDataMeta(){
        BandRepository.showBandDataMeta();
    }

    public static List<Band> findBandByName(String name){
        return BandRepository.findByName(name);
    }

    public static void saveManyBands(List<Band> bands){
        if (bands == null || bands.isEmpty()){
            throw new IllegalArgumentException("List of Bands cant not be empty");
        }
        for (Band band : bands){
            requireValidId(band.getFk_leader());
        }
        BandRepository.saveManyBands(bands);
    }

    public  static  void requireValidId(Integer id){
        if (id == null || id <= 0){
            throw new IllegalArgumentException("id cant not be null");
        }
    }
}
