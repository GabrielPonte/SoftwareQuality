<template>
  <div>
    <h2 id="page-heading" data-cy="InformacoesHeading">
      <span v-text="$t('softwareQualityApp.informacoes.home.title')" id="informacoes-heading">Informacoes</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('softwareQualityApp.informacoes.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && informacoes && informacoes.length === 0">
      <span v-text="$t('softwareQualityApp.informacoes.home.notFound')">No informacoes found</span>
    </div>
    <div class="table-responsive" v-if="informacoes && informacoes.length > 0">
      <table class="table table-striped" aria-describedby="informacoes">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.nomeCompleto')">Nome Completo</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.cpf')">Cpf</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.email')">Email</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.idade')">Idade</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.sintomas')">Sintomas</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.qtdVacinas')">Qtd Vacinas</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.cidade')">Cidade</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.estado')">Estado</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.endereco')">Endereco</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.complemento')">Complemento</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.cep')">Cep</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.data')">Data</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.hora')">Hora</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.localDesejado')">Local Desejado</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.opcaoEscolhida')">Opcao Escolhida</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.postoSaude')">Posto Saude</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.recebaEmail')">Receba Email</span></th>
            <th scope="row"><span v-text="$t('softwareQualityApp.informacoes.tipoInformacao')">Tipo Informacao</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="informacoes in informacoes" :key="informacoes.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'InformacoesView', params: { informacoesId: informacoes.id } }">{{ informacoes.id }}</router-link>
            </td>
            <td>{{ informacoes.nomeCompleto }}</td>
            <td>{{ informacoes.cpf }}</td>
            <td>{{ informacoes.email }}</td>
            <td>{{ informacoes.idade }}</td>
            <td>{{ informacoes.sintomas }}</td>
            <td>{{ informacoes.qtdVacinas }}</td>
            <td>{{ informacoes.cidade }}</td>
            <td>{{ informacoes.estado }}</td>
            <td>{{ informacoes.endereco }}</td>
            <td>{{ informacoes.complemento }}</td>
            <td>{{ informacoes.cep }}</td>
            <td>{{ informacoes.data }}</td>
            <td>{{ informacoes.hora }}</td>
            <td>
              <div v-if="informacoes.localDesejado">
                <router-link :to="{ name: 'LocalDesejadoView', params: { localDesejadoId: informacoes.localDesejado.id } }">{{
                  informacoes.localDesejado.localCovid
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="informacoes.opcaoEscolhida">
                <router-link :to="{ name: 'OpcaoEscolhidaView', params: { opcaoEscolhidaId: informacoes.opcaoEscolhida.id } }">{{
                  informacoes.opcaoEscolhida.opcaoInicial
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="informacoes.postoSaude">
                <router-link :to="{ name: 'PostoSaudeView', params: { postoSaudeId: informacoes.postoSaude.id } }">{{
                  informacoes.postoSaude.nomePosto
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="informacoes.recebaEmail">
                <router-link :to="{ name: 'RecebaEmailView', params: { recebaEmailId: informacoes.recebaEmail.id } }">{{
                  informacoes.recebaEmail.opcaoEmail
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="informacoes.tipoInformacao">
                <router-link :to="{ name: 'TipoInformacaoView', params: { tipoInformacaoId: informacoes.tipoInformacao.id } }">{{
                  informacoes.tipoInformacao.tipoInfo
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'InformacoesView', params: { informacoesId: informacoes.id } }" custom v-slot="{ navigate }">
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
        ><span
          id="softwareQualityApp.informacoes.delete.question"
          data-cy="informacoesDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-informacoes-heading" v-text="$t('softwareQualityApp.informacoes.delete.question', { id: removeId })">
          Are you sure you want to delete this Informacoes?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-informacoes"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeInformacoes()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./informacoes.component.ts"></script>
