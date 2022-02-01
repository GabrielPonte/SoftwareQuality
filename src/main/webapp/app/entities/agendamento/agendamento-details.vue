<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="agendamento">
        <h2 class="jh-entity-heading" data-cy="agendamentoDetailsHeading">
          <span v-text="$t('jhipsterApp.agendamento.detail.title')">Agendamento</span> {{ agendamento.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.nomeCompleto')">Nome Completo</span>
          </dt>
          <dd>
            <span>{{ agendamento.nomeCompleto }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.CPF')">CPF</span>
          </dt>
          <dd>
            <span>{{ agendamento.CPF }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.idade')">Idade</span>
          </dt>
          <dd>
            <span>{{ agendamento.idade }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.sintomas')">Sintomas</span>
          </dt>
          <dd>
            <span>{{ agendamento.sintomas }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.qtdVacinas')">Qtd Vacinas</span>
          </dt>
          <dd>
            <span>{{ agendamento.qtdVacinas }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.cidade')">Cidade</span>
          </dt>
          <dd>
            <span>{{ agendamento.cidade }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.estado')">Estado</span>
          </dt>
          <dd>
            <span>{{ agendamento.estado }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.endereco')">Endereco</span>
          </dt>
          <dd>
            <span>{{ agendamento.endereco }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.complemento')">Complemento</span>
          </dt>
          <dd>
            <span>{{ agendamento.complemento }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.CEP')">CEP</span>
          </dt>
          <dd>
            <span>{{ agendamento.CEP }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.nomePosto')">Nome Posto</span>
          </dt>
          <dd>
            <span>{{ agendamento.nomePosto }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.data')">Data</span>
          </dt>
          <dd>
            <span>{{ agendamento.data }}</span>
          </dd>
          <dt>
            <span v-text="$t('jhipsterApp.agendamento.hora')">Hora</span>
          </dt>
          <dd>
            <span>{{ agendamento.hora }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./agendamento-details.component.ts"></script>
