package com.example.mangxahoi.service.image;

import com.example.mangxahoi.model.Image;
import com.example.mangxahoi.service.IGeneralService;

public interface IServiceImage extends IGeneralService<Image> {
    Image add(Image post);
}
