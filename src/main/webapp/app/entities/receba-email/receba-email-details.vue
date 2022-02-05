<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="recebaEmail">
        <h2 class="jh-entity-heading" data-cy="recebaEmailDetailsHeading">
          <span v-text="$t('softwareQualityApp.recebaEmail.detail.title')">RecebaEmail</span> {{ recebaEmail.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('softwareQualityApp.recebaEmail.opcaoEmail')">Opcao Email</span>
          </dt>
          <dd>
            <span>{{ recebaEmail.opcaoEmail }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="recebaEmail.id"
          :to="{ name: 'RecebaEmailEdit', params: { recebaEmailId: recebaEmail.id } }"
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

<script lang="ts" src="./receba-email-details.component.ts"></script>
