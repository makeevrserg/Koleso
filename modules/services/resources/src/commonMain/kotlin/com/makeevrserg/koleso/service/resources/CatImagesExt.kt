package com.makeevrserg.koleso.service.resources

import com.makeevrserg.koleso.resources.MR
import io.github.skeptick.libres.images.Image

object CatImagesExt {
    val MR.catImages: Array<Image>
        get() = arrayOf(
            MR.image.cat_black,
            MR.image.cat_calico,
            MR.image.cat_red,
            MR.image.cat_white,
            MR.image.img_cat_jellie,
            MR.image.img_cat_ocelot,
            MR.image.img_cat_persian,
            MR.image.img_cat_ragdoll,
            MR.image.cat_red,
            MR.image.img_cat_shorthair,
            MR.image.img_cat_siamese,
            MR.image.img_cat_tabby,
            MR.image.img_cat_tuxedo,
            MR.image.cat_white
        )
}