package com.myharbour.service;

public interface SubmitService {

    /**
     * @param containerId
     * @param userId
     * @param type
     * @param size
     */

    //货主提交一个空箱子
    void submitAnEmptyContainer(Integer containerId,
                                Integer userId,
                                Integer type,
                                Integer size);


    //货主提交一个带货的箱子
    void submitAContainerWithCargos(Integer cargo_type, Integer size, Integer amount);

    /*散货 /sǎn huò/
简明 新汉英 现代汉语 例句 百科
[贸易] bulk cargo
散装货；*/
    //货主提交一份散货
    void submitBulkCargo(Integer cargoType);
}
