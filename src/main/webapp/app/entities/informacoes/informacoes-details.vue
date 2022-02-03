<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="informacoes">
        <h2 class="jh-entity-heading" data-cy="informacoesDetailsHeading">
          <span v-text="$t('softwareQualityApp.informacoes.detail.title')">Informacoes</span> {{ informacoes.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.opcao')">Opcao</span>
          </dt>
          <dd>
            <span>{{ informacoes.opcao }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.nomeCompleto')">Nome Completo</span>
          </dt>
          <dd>
            <span>{{ informacoes.nomeCompleto }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.cpf')">Cpf</span>
          </dt>
          <dd>
            <span>{{ informacoes.cpf }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.email')">Email</span>
          </dt>
          <dd>
            <span>{{ informacoes.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.idade')">Idade</span>
          </dt>
          <dd>
            <span>{{ informacoes.idade }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.sintomas')">Sintomas</span>
          </dt>
          <dd>
            <span>{{ informacoes.sintomas }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.qtdVacinas')">Qtd Vacinas</span>
          </dt>
          <dd>
            <span>{{ informacoes.qtdVacinas }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.cidade')">Cidade</span>
          </dt>
          <dd>
            <span>{{ informacoes.cidade }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.estado')">Estado</span>
          </dt>
          <dd>
            <span>{{ informacoes.estado }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.endereco')">Endereco</span>
          </dt>
          <dd>
            <span>{{ informacoes.endereco }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.complemento')">Complemento</span>
          </dt>
          <dd>
            <span>{{ informacoes.complemento }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.cep')">Cep</span>
          </dt>
          <dd>
            <span>{{ informacoes.cep }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.nomeDoPosto')">Nome Do Posto</span>
          </dt>
          <dd>
            <span>{{ informacoes.nomeDoPosto }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.estadoDoPosto')">Estado Do Posto</span>
          </dt>
          <dd>
            <span>{{ informacoes.estadoDoPosto }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.cidadeDoPosto')">Cidade Do Posto</span>
          </dt>
          <dd>
            <span>{{ informacoes.cidadeDoPosto }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.data')">Data</span>
          </dt>
          <dd>
            <span>{{ informacoes.data }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.hora')">Hora</span>
          </dt>
          <dd>
            <span>{{ informacoes.hora }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.tipoDeInformacao')">Tipo De Informacao</span>
          </dt>
          <dd>
            <span>{{ informacoes.tipoDeInformacao }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.local')">Local</span>
          </dt>
          <dd>
            <span>{{ informacoes.local }}</span>
          </dd>
          <dt>
            <span v-text="$t('softwareQualityApp.informacoes.receberEmail')">Receber Email</span>
          </dt>
          <dd>
            <span>{{ informacoes.receberEmail }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./informacoes-details.component.ts"></script>
