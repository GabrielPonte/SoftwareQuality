<template>
  <div>
    <h2 id="page-heading" data-cy="OpcaoEscolhidaHeading">
      <span v-text="$t('softwareQualityApp.opcaoEscolhida.home.title')" id="opcao-escolhida-heading">Opcao Escolhidas</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('softwareQualityApp.opcaoEscolhida.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'OpcaoEscolhidaCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-opcao-escolhida"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('softwareQualityApp.opcaoEscolhida.home.createLabel')"> Create a new Opcao Escolhida </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && opcaoEscolhidas && opcaoEscolhidas.length === 0">
      <span v-text="$t('softwareQualityApp.opcaoEscolhida.home.notFound')">No opcaoEscolhidas found</span>
    </div>
    <div class="table-responsive" v-if="opcaoEscolhidas && opcaoEscolhidas.length > 0">
      <table class="table table-striped" aria-describedby="opcaoEscolhidas">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.opcaoEscolhida.opcaoInicial')">Opcao Inicial</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="opcaoEscolhida in opcaoEscolhidas" :key="opcaoEscolhida.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OpcaoEscolhidaView', params: { opcaoEscolhidaId: opcaoEscolhida.id } }">{{
                opcaoEscolhida.id
              }}</router-link>
            </td>
            <td>{{ opcaoEscolhida.opcaoInicial }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OpcaoEscolhidaView', params: { opcaoEscolhidaId: opcaoEscolhida.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OpcaoEscolhidaEdit', params: { opcaoEscolhidaId: opcaoEscolhida.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(opcaoEscolhida)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span
          id="softwareQualityApp.opcaoEscolhida.delete.question"
          data-cy="opcaoEscolhidaDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-opcaoEscolhida-heading" v-text="$t('softwareQualityApp.opcaoEscolhida.delete.question', { id: removeId })">
          Are you sure you want to delete this Opcao Escolhida?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-opcaoEscolhida"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeOpcaoEscolhida()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./opcao-escolhida.component.ts"></script>
