/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AgendamentoDetailComponent from '@/entities/agendamento/agendamento-details.vue';
import AgendamentoClass from '@/entities/agendamento/agendamento-details.component';
import AgendamentoService from '@/entities/agendamento/agendamento.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Agendamento Management Detail Component', () => {
    let wrapper: Wrapper<AgendamentoClass>;
    let comp: AgendamentoClass;
    let agendamentoServiceStub: SinonStubbedInstance<AgendamentoService>;

    beforeEach(() => {
      agendamentoServiceStub = sinon.createStubInstance<AgendamentoService>(AgendamentoService);

      wrapper = shallowMount<AgendamentoClass>(AgendamentoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { agendamentoService: () => agendamentoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAgendamento = { id: 123 };
        agendamentoServiceStub.find.resolves(foundAgendamento);

        // WHEN
        comp.retrieveAgendamento(123);
        await comp.$nextTick();

        // THEN
        expect(comp.agendamento).toBe(foundAgendamento);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAgendamento = { id: 123 };
        agendamentoServiceStub.find.resolves(foundAgendamento);

        // WHEN
        comp.beforeRouteEnter({ params: { agendamentoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.agendamento).toBe(foundAgendamento);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
