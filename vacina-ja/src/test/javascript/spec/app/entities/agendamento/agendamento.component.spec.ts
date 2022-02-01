/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import AgendamentoComponent from '@/entities/agendamento/agendamento.vue';
import AgendamentoClass from '@/entities/agendamento/agendamento.component';
import AgendamentoService from '@/entities/agendamento/agendamento.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Agendamento Management Component', () => {
    let wrapper: Wrapper<AgendamentoClass>;
    let comp: AgendamentoClass;
    let agendamentoServiceStub: SinonStubbedInstance<AgendamentoService>;

    beforeEach(() => {
      agendamentoServiceStub = sinon.createStubInstance<AgendamentoService>(AgendamentoService);
      agendamentoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<AgendamentoClass>(AgendamentoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          agendamentoService: () => agendamentoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      agendamentoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllAgendamentos();
      await comp.$nextTick();

      // THEN
      expect(agendamentoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.agendamentos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
