package com.mx.rfid.datamodule.service.impl;

import com.mx.rfid.datamodule.entity.Product;
import com.mx.rfid.datamodule.mapper.ProductMapper;
import com.mx.rfid.datamodule.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mx
 * @since 2021-01-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
