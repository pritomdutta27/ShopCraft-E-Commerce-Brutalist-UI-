package site.pritom.features.home.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import site.pritom.features.home.data.datasource.ProductApiService
import site.pritom.features.home.data.repository.ProductRepositoryImpl
import site.pritom.features.home.domain.repository.ProductRepository
import javax.inject.Singleton

/** Created by Pritom Dutta on 1/10/26 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ProductModule {

    @Binds
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository

    companion object {
        @Provides
        @Singleton
        fun provideProductApiService(retrofit: Retrofit): ProductApiService {
            return retrofit.create(ProductApiService::class.java)
        }
    }
}