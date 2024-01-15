package com.makeevrserg.koleso.service.resources

import com.makeevrserg.koleso.resources.MR
import dev.icerock.moko.resources.ImageResource

object CatImagesExt {
    val MR.catImages: Array<ImageResource>
        get() = arrayOf(
            MR.images.cat_black,
            MR.images.cat_calico,
            MR.images.cat_red,
            MR.images.cat_white,
            MR.images.img_cat_jellie,
            MR.images.img_cat_ocelot,
            MR.images.img_cat_persian,
            MR.images.img_cat_ragdoll,
            MR.images.cat_red,
            MR.images.img_cat_shorthair,
            MR.images.img_cat_siamese,
            MR.images.img_cat_tabby,
            MR.images.img_cat_tuxedo,
            MR.images.cat_white
        )
}