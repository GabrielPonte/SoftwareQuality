<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="localDesejado">
        <h2 class="jh-entity-heading" data-cy="localDesejadoDetailsHeading">
          <span v-text="$t('softwareQualityApp.localDesejado.detail.title')">LocalDesejado</span> {{ localDesejado.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('softwareQualityApp.localDesejado.localCovid')">Local Covid</span>
          </dt>
          <dd>
            <span>{{ localDesejado.localCovid }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="localDesejado.id"
          :to="{ name: 'LocalDesejadoEdit', params: { localDesejadoId: localDesejado.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./local-desejado-details.component.ts"></script>
