package com.myharbour.dao;

import com.myharbour.pojo.Container;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerMapper {
    /**
     * container
     英/kən'teɪnə/  美/kən'tenɚ/  全球(美国)
     简明 朗文 柯林斯 21世纪 例句 百科
     n. 集装箱；容器
     CET4 | 商务英语
     *
     * @param containerId
     * @param containerArea
     * @param userId
     * @param row
     * @param column
     * @param layer
     * @param type
     * @param size
     * @param rowBounds
     * @return
     */
    //不加valid默认查询所有valid为true(1)的列！
    List<Container> getContainers(@Param("containerId") Integer containerId,
                                       @Param("containerArea") Integer containerArea,
                                       @Param("userId") Integer userId,
                                       @Param("row") Integer row,
                                       @Param("column") Integer column,
                                       @Param("layer") Integer layer,
                                       @Param("type") Integer type,
                                       @Param("size") Integer size
            , RowBounds rowBounds);

    List<Container> getContainersWithEmptyStatus(@Param("containerId") Integer containerId,
                                                 @Param("containerArea") Integer containerArea,
                                                 @Param("userId") Integer userId,
                                                 @Param("row") Integer row,
                                                 @Param("column") Integer column,
                                                 @Param("layer") Integer layer,
                                                 @Param("type") Integer type,
                                                 @Param("size") Integer size
            , RowBounds rowBounds);

    List<Container> getInvalidContainers(@Param("containerId") Integer containerId,
                                         @Param("containerArea") Integer containerArea,
                                         @Param("userId") Integer userId,
                                         @Param("row") Integer row,
                                         @Param("column") Integer column,
                                         @Param("layer") Integer layer,
                                         @Param("type") Integer type,
                                         @Param("size") Integer size
            , RowBounds rowBounds);

    /**
     *
     * @param id
     * @param rowBounds
     * @return
     */
    List<Container> getContainerById(@Param("id") Integer id, RowBounds rowBounds);

    /**
     *
     * @param container
     */
    void insertContainer(Container container);

    /**
     *
     * @param container
     */
    void updateContainer(Container container);

    void  updateContainerToInvalidById(Integer containerId);
}
