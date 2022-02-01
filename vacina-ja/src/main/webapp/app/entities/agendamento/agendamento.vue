<template>
  <div>
    <h2 id="page-heading" data-cy="AgendamentoHeading">
      <span v-text="$t('jhipsterApp.agendamento.home.title')" id="agendamento-heading">Agendamentos</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('jhipsterApp.agendamento.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && agendamentos && agendamentos.length === 0">
      <span v-text="$t('jhipsterApp.agendamento.home.notFound')">No agendamentos found</span>
    </div>
    <div class="table-responsive" v-if="agendamentos && agendamentos.length > 0">
      <table class="table table-striped" aria-describedby="agendamentos">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.nomeCompleto')">Nome Completo</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.CPF')">CPF</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.idade')">Idade</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.sintomas')">Sintomas</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.qtdVacinas')">Qtd Vacinas</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.cidade')">Cidade</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.estado')">Estado</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.endereco')">Endereco</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.complemento')">Complemento</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.CEP')">CEP</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.nomePosto')">Nome Posto</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.data')">Data</span></th>
            <th scope="row"><span v-text="$t('jhipsterApp.agendamento.hora')">Hora</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="agendamento in agendamentos" :key="agendamento.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AgendamentoView', params: { agendamentoId: agendamento.id } }">{{ agendamento.id }}</router-link>
            </td>
            <td>{{ agendamento.nomeCompleto }}</td>
            <td>{{ agendamento.CPF }}</td>
            <td>{{ agendamento.idade }}</td>
            <td>{{ agendamento.sintomas }}</td>
            <td>{{ agendamento.qtdVacinas }}</td>
            <td>{{ agendamento.cidade }}</td>
            <td>{{ agendamento.estado }}</td>
            <td>{{ agendamento.endereco }}</td>
            <td>{{ agendamento.complemento }}</td>
            <td>{{ agendamento.CEP }}</td>
            <td>{{ agendamento.nomePosto }}</td>
            <td>{{ agendamento.data }}</td>
            <td>{{ agendamento.hora }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AgendamentoView', params: { agendamentoId: agendamento.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="jhipsterApp.agendamento.delete.question" data-cy="agendamentoDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-agendamento-heading" v-text="$t('jhipsterApp.agendamento.delete.question', { id: removeId })">
          Are you sure you want to delete this Agendamento?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-agendamento"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeAgendamento()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./agendamento.component.ts"></script>
