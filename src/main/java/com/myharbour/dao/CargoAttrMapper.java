package com.myharbour.dao;

import com.myharbour.pojo.CargoAttr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoAttrMapper {

    /**
     * attribute
     英/(for n.) ˈatrɪbjuːt; (for v.) əˈtrɪbjuːt/  美/(for n.) ˈætrəˌbjut; (for v.) əˈtrɪbjut/  全球(美国)
     简明 朗文 柯林斯 21世纪 例句 百科
     n. 属性；特质
     vt. 归属；把…归于
     过去式 attributed过去分词 attributed现在分词 attributing
     CET6 | SAT | 考研 | CET4 | GRE | IELTS | GMAT | 商务英语 | TOEFL
     *
     * @param cargoTypeId
     * @param cargoName
     * @param containerType
     * @param unitType
     * @param rowBounds
     * @return
     */
    List<CargoAttr> getCargoAttrs(@Param("cargoTypeId") Integer cargoTypeId,
                                  @Param("cargoName") String cargoName,
                                  @Param("containerType") Integer containerType,
                                  @Param("unitType") String unitType,
                                  RowBounds rowBounds);

    /**
     *
     * @param cargoTypeId
     * @param cargoName
     * @param containerType
     * @param unitType
     * @param rowBounds
     * @return
     */
    List<CargoAttr> getInvalidCargoAttrs(@Param("cargoTypeId") Integer cargoTypeId,
                                         @Param("cargoName") String cargoName,
                                         @Param("containerType") Integer containerType,
                                         @Param("unitType") String unitType,
                                         RowBounds rowBounds);

    /**
     *
     * @param id
     * @param rowBounds
     * @return
     */
    CargoAttr getCargoAttrById(@Param("id") Integer id, RowBounds rowBounds);

    /**
     *
     * @param cargoAttr
     */
    void insertCargoAttr(CargoAttr cargoAttr);

    /**
     *
     * @param cargoAttr
     */
    void updateCargoAttr(CargoAttr cargoAttr);
}
