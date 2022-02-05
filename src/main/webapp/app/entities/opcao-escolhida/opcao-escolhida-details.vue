<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="opcaoEscolhida">
        <h2 class="jh-entity-heading" data-cy="opcaoEscolhidaDetailsHeading">
          <span v-text="$t('softwareQualityApp.opcaoEscolhida.detail.title')">OpcaoEscolhida</span> {{ opcaoEscolhida.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('softwareQualityApp.opcaoEscolhida.opcaoInicial')">Opcao Inicial</span>
          </dt>
          <dd>
            <span>{{ opcaoEscolhida.opcaoInicial }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="opcaoEscolhida.id"
          :to="{ name: 'OpcaoEscolhidaEdit', params: { opcaoEscolhidaId: opcaoEscolhida.id } }"
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

<script lang="ts" src="./opcao-escolhida-details.component.ts"></script>
